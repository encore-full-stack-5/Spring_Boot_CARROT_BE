package com.example.carrot_market.product.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.carrot_market.area.db.AreaMapper;
import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.area.domain.model.AreaRange;
import com.example.carrot_market.core.error.CommonError;
import com.example.carrot_market.product.domain.*;
import com.example.carrot_market.product.domain.ProductResponseDto;
import com.example.carrot_market.product.dto.*;
import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.product.domain.ProductAggregate;
import com.example.carrot_market.product.domain.ProductCategory;
import com.example.carrot_market.product.domain.ProductImage;

import com.example.carrot_market.product.repository.ImageMapper;
import com.example.carrot_market.product.repository.ProductMapper;
import com.example.carrot_market.user.db.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Log4j2
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductMapper productMapper;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final AmazonS3 s3client;
    @Autowired
    private final ImageMapper imageMapper;

    @Autowired
    private final AreaMapper areaMapper;

    @Override
    @Transactional
    public Product insertProduct(
            InsertProductRequestDto insertProductRequestDto,
            MultipartFile[] files
    ) {
        Product product = insertProductRequestDto.toDomain(insertProductRequestDto, 1);
        productMapper.insertProduct(product);
        if (files == null) {
            return product;
        } else {
            return createProductWithImages(product, files);
        }
    }

    @Override
    public void updateProduct(int id, UpdateProductRequestDto req) {
        Product product = req.toEntity(id);
        productMapper.updateProduct(product);
    }

    @Override
    public List<ProductCategory> getProductCategories() {
        return productMapper.getProductCategories();
    }

    @Override
    public void refreshProduct(int productId) {
        productMapper.refreshProduct(productId);
    }

    @Transactional
    @Override
    public FetchProductResult fetchProducts(int category, int areaId, int limit, int lastProductId, AreaRange areaRange) {
        List<ProductResponseDto> products = productMapper.findProductsByCategoryAndArea(category, areaId, limit, lastProductId, areaRange.getDistance());
        if(products == null || products.isEmpty()) {
            return FetchProductResult.builder()
                    .lastId(0)
                    .result(List.of())
                    .build();
        }

        List<ProductImage> images = imageMapper.findImagesByProductIds(products.stream().map(ProductResponseDto::getId).toList());
        List<FetchProductDto> productAggregateList = products.stream().map(product ->
                new FetchProductDto(
                        product,
                        false,
                        0,
                        images.stream().filter(image -> image.getType_id() == product.getId()).toList()
                )).toList();
        return new FetchProductResult(productAggregateList, products.getLast().getId());
    }

    // 사용자가 등록한 상품 조회
    @Override
    public List<Product> getProductsByUserId(int userId, int offset, int limit) {
        return productMapper.getProductByUserId(userId, offset, limit);
    }

    // 상품 삭제
    @Override
    public Product deleteProduct(int productId) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp deletedAt = Timestamp.valueOf(now);
        Optional<Product> productById = productMapper.selectProductById(productId);
        // productId 유무
        if (productById.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist product");

        Product getProduct = productById.get();
        Product product = Product.builder()
                .id(getProduct.getId())
                .sellerId(getProduct.getSellerId())
                .sellingAreaId(getProduct.getSellingAreaId())
                .categoryId(getProduct.getCategoryId())
                .isNegotiation(getProduct.getIsNegotiation())
                .createdAt(getProduct.getCreatedAt())
                .state(getProduct.getState())
                .viewCount(getProduct.getViewCount())
                .title(getProduct.getTitle())
                .content(getProduct.getContent())
                .refreshedAt(getProduct.getRefreshedAt())
                .price(getProduct.getPrice())
                .deletedAt(String.valueOf(deletedAt))
                .likeCount(getProduct.getLikeCount())
                .build();
        productMapper.deleteProduct(product);
        return product;
    }

    @Override
    public void likeProduct(InsertLikeCountRequestDto req) {
//        Optional<Product> product = productMapper.selectProductById(req.productId());
//        if(product.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist product");
//        Optional<User> user = userMapper.selectUserById(req.userId());
//        if(user.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist user");
        Optional<Like> likeExist = productMapper.selectLike(req);
        if (!likeExist.isEmpty()) {
            likeProductCancel(req);
        } else {
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            // 상품의 type 은 1 이라고 가정
            // typeId 는 상품의 Id 라고 가정
            Like like = Like.builder()
                    .id(0)
                    .userId(req.userId())
                    .typeId(req.productId())
                    .type(1)
                    .createdAt(timestamp)
                    .build();
            productMapper.insertLikeCount(like);
            productMapper.updateLikeCountProduct(req.productId());
        }
    }

    @Override
    @Transactional
    public void likeProductCancel(InsertLikeCountRequestDto req) {
        productMapper.deleteLikeCount(req);
        productMapper.updateLikeCountProductMinus(req.productId());
    }

    // 상품 조회수
    @Override
    public boolean increaseViewCount(int productId) {
        return productMapper.increaseViewCount(productId);
    }

    @Override
    public void updateProductStatus(int id, int state) {
        productMapper.updateProductStatus(id, state);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductAggregate selectProductById(int productId) {
        Optional<Product> product = productMapper.selectProductById(productId);
        if(product.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist product");

        List<ProductImage> images = imageMapper.findImagesByProductIdOne(productId);
        Optional<String> nickname = userMapper.getNickname(product.get().getSellerId());
        double userScore = userMapper.getUserScore(product.get().getSellerId());
        if(nickname.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist user");
        Optional<Area> areaName = areaMapper.getAreaName(product.get().getSellingAreaId());
        String categoryName = productMapper.getCategoryName(product.get().getCategoryId());
        return new ProductAggregate(
                        product.get(),
                        false,
                        0,
                        images.stream().toList(),
                        nickname.get(),
                        areaName.get(),
                        userScore,
                        categoryName
                );
    }

    private Product createProductWithImages(Product product, MultipartFile[] images) {
//        List<CompletableFuture<Boolean>> uploadFutures = new ArrayList<>();
        for (MultipartFile file : images) {
            String imageFileName = makeProductImageName(file, product.getId());
            ProductImage image = ProductImage.builder()
                    .id(0)
                    .type(1)
                    .type_id(product.getId())
                    .file_path(imageFileName)
                    .build();
            imageMapper.insertProductImage(image);
//            CompletableFuture<Boolean> future = uploadFileToS3(file, imageFileName);
            uploadFileToS3Sync(file, imageFileName);
        }

        // 비동기 업로드 처리
//        CompletableFuture<Void> allUploads = CompletableFuture.allOf(uploadFutures.toArray(new CompletableFuture[0]));
//        allUploads.thenRunAsync(() -> {
//            List<Boolean> results = uploadFutures.stream()
//                    .map(CompletableFuture::join)
//                    .toList();
//            if (results.contains(false)) {
//                productMapper.updateProductStatus(product.getId(), 5);
//            } else {
//                productMapper.updateProductStatus(product.getId(), 1);
//            }
//        });

        return product;
    }

    private List<ProductImage> findImageByTypeAndTypeId(int type, int typeId) {
        return imageMapper.findImageByTypeAndTypeId(type, typeId);
    }

    private String makeProductImageName(MultipartFile file, int productId) {
        return "product/" + productId + "/" + file.getOriginalFilename();
    }

    @Async
    private CompletableFuture<Boolean> uploadFileToS3(MultipartFile file, String imageFileName) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            s3client.putObject(new PutObjectRequest("carrotmarket", imageFileName, file.getInputStream(), metadata));
            return CompletableFuture.completedFuture(true);
        } catch (IOException e) {
            return CompletableFuture.completedFuture(false);
        } catch (Exception e) {
            System.err.println("Error uploading file to S3: " + e.getMessage());
            return CompletableFuture.completedFuture(false);
        }
    }

    private void uploadFileToS3Sync(MultipartFile file, String imageFileName) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            s3client.putObject(new PutObjectRequest("carrotmarket", imageFileName, file.getInputStream(), metadata));
        } catch (Exception e) {
            log.error("S3 이미지 업로드 중 오류 발생");
            throw new CommonError.Unexpected.ServerError("이미지 업로드에 실패하였습니다.");
        }
    }

}
