package com.example.carrot_market.product.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Profile("dev")
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    void insertProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void getProductCategories() {
    }

    @Test
    void refreshProduct() {
    }

    @Test
    void fetchProducts() {
    }

    @Test
    void getProductsByUserId() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void likeProduct() {
    }

    @Test
    void increaseViewCount() {
    }

    @Test
    void updateProductStatus() {
        productService.updateProductStatus(28, 1);
    }
}