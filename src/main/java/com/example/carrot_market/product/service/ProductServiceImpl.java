package com.example.carrot_market.product.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.product.domain.ProductCategory;
import com.example.carrot_market.product.domain.ProductImage;
import com.example.carrot_market.product.dto.InsertProductRequestDto;
import com.example.carrot_market.product.dto.UpdateProductRequestDto;
import com.example.carrot_market.product.repository.ImageMapper;
import com.example.carrot_market.product.repository.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
        if(files == null) {
            return product;
        }
        return createProductWithImages(product, files);
    }

    private Product createProductWithImages(Product product, MultipartFile[] images) {
        productMapper.insertProduct(product);
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
        return "uploads/" + productId + "/" + file.getOriginalFilename();
    }

    @Async
    public CompletableFuture<Boolean> uploadFileToS3(MultipartFile file, String imageFileName) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            s3client.putObject(new PutObjectRequest("carrotmarket", imageFileName, file.getInputStream(), metadata));
            return CompletableFuture.completedFuture(true);
        } catch (IOException e) {
            return CompletableFuture.completedFuture(false);
        } catch (Exception e) {
            System.err.println("Error uploading file to S3: " + e.getMessage());
            return CompletableFuture.completedFuture(false);
        }
    }

//    @Async
//    private CompletableFuture<Void> uploadFileToS3(MultipartFile[] files, int productId, List<String> keyNames) {
//        try {
//            for(int i=0;i< files.length;i++) {
//                MultipartFile file = files[i];
//                String keyName = keyNames.get(i);
//                ObjectMetadata metadata = new ObjectMetadata();
//                metadata.setContentLength(file.getSize());
//                s3client.putObject(new PutObjectRequest("your-bucket-name", keyName, file.getInputStream(), metadata));
//            }
//            return CompletableFuture.completedFuture(true);
//        } catch (Exception e) {
//            productMapper.updateStatus(productId, "failed", null);
//            return CompletableFuture.completedFuture(null);
//        }
//    }

    @Override
    public Product updateProduct(UpdateProductRequestDto updateProductRequestDto) {
        return null;
    }

    @Override
    public List<ProductCategory> getProductCategories() {
        return null;
    }

    @Override
    public Product refreshProduct(int productId) {
        return null;
    }

    @Override
    public List<Product> fetchProducts(int categoryId, int areaId, int offset, int limit) {
        return null;
    }

    @Override
    public List<Product> getProductsByUserId(int userId, int offset, int limit) {
        return null;
    }

    @Override
    public boolean deleteProduct(int productId) {
        return false;
    }

    @Override
    public boolean likeProduct(int productId, int userId) {
        return false;
    }

    @Override
    public boolean increaseViewCount(int productId) {
        return false;
    }
}
