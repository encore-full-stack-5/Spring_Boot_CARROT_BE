package com.example.carrot_market.product.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.carrot_market.area.domain.model.AreaRange;
import com.example.carrot_market.core.CommonError;
import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.product.domain.ProductAggregate;
import com.example.carrot_market.product.domain.ProductCategory;
import com.example.carrot_market.product.domain.ProductImage;
import com.example.carrot_market.product.dto.InsertProductRequestDto;
import com.example.carrot_market.product.dto.UpdateProductRequestDto;
import com.example.carrot_market.product.repository.ImageMapper;
import com.example.carrot_market.product.repository.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductMapper productMapper;
    @Autowired
    private final AmazonS3 s3client;
    @Autowired
    private final ImageMapper imageMapper;

    @Override
    @Transactional
    public Product insertProduct(
            InsertProductRequestDto insertProductRequestDto,
            MultipartFile[] files
    ) {
        Product product = makeProductByDto(insertProductRequestDto);
        productMapper.insertProduct(product);
        if (files == null) {
            return product;
        }
        return createProductWithImages(product, files);
    }

    @Override
    public Product updateProduct(UpdateProductRequestDto updateProductRequestDto) {
        return null;
    }

    @Override
    public List<ProductCategory> getProductCategories() {
        return productMapper.getProductCategories();
    }

    @Override
    public Product refreshProduct(int productId) {
        return null;
    }

    @Transactional
    @Override
    public List<ProductAggregate> fetchProducts(int category, int areaId, int limit, int lastProductId, AreaRange areaRange) {
        List<Product> products = productMapper.findProductsByCategoryAndArea(
                category,
                areaId,
                limit,
                lastProductId,
                areaRange.getDistance()
        );

        List<ProductImage> images = imageMapper.findImagesByProductIds(products.stream().map(Product::getId).toList());
        return products.stream().map(product ->
                new ProductAggregate(
                        product,
                        false,
                        0,
                        images.stream().filter(image -> image.getType_id() == product.getId()).toList()
                )).toList();
    }

    @Override
    public List<Product> getProductsByUserId(int userId, int offset, int limit) {
        return null;
    }

    // 상품 삭제
    @Override
    public Product deleteProduct(int productId) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp deletedAt = Timestamp.valueOf(now);
        Optional<Product> productById = productMapper.selectProductById(productId);
        // productId 유무
        if(productById.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist product");

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
    public boolean likeProduct(int productId, int userId) {
        return false;
    }

    @Override
    public boolean increaseViewCount(int productId) {
        return false;
    }

    @Override
    @Transactional
    public void updateProductStatus(int id, int state) {
        productMapper.updateProductStatus(id, state);
    }

    private Product createProductWithImages(Product product, MultipartFile[] images) {
        List<CompletableFuture<Boolean>> uploadFutures = new ArrayList<>();
        for (MultipartFile file : images) {
            String imageFileName = makeProductImageName(file, product.getId());
            ProductImage image = ProductImage.builder()
                    .id(0)
                    .type(1)
                    .type_id(product.getId())
                    .file_path(imageFileName)
                    .build();
            imageMapper.insertProductImage(image);
            CompletableFuture<Boolean> future = uploadFileToS3(file, imageFileName);
            uploadFutures.add(future);
        }

        CompletableFuture<Void> allUploads = CompletableFuture.allOf(uploadFutures.toArray(new CompletableFuture[0]));
        allUploads.thenRunAsync(() -> {
            List<Boolean> results = uploadFutures.stream()
                    .map(CompletableFuture::join)
                    .toList();
            if (results.contains(false)) {
                productMapper.updateProductStatus(product.getId(), 5);
            } else {
                productMapper.updateProductStatus(product.getId(), 1);
            }
        });

        return product;
    }

    private List<ProductImage> findImageByTypeAndTypeId(int type, int typeId) {
        return imageMapper.findImageByTypeAndTypeId(type, typeId);
    }

    private static Product makeProductByDto(InsertProductRequestDto dto) {
        return Product.builder()
                .id(0)
                .sellerId(dto.userId())
                .sellingAreaId(dto.areaId())
                .categoryId(dto.categoryId())
                .isNegotiation(dto.isNegotiation())
                .state(4)
                .title(dto.title())
                .content(dto.content())
                .price(dto.price())
                .build();
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

}
