package com.example.carrot_market.product.service;

import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.product.dto.CreateProductRequestDto;
import com.example.carrot_market.product.dto.UpdateProductRequestDto;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public Product createProduct(CreateProductRequestDto createProductRequestDto) {
        return null;
    }

    @Override
    public Product updateProduct(UpdateProductRequestDto updateProductRequestDto) {
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
