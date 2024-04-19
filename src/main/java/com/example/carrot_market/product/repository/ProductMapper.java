package com.example.carrot_market.product.repository;

import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.product.domain.ProductCategory;
import com.example.carrot_market.product.dto.InsertProductRequestDto;
import com.example.carrot_market.product.dto.UpdateProductRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    void insertProduct(Product product);

    Product updateProduct(UpdateProductRequestDto updateProductRequestDto);

    List<ProductCategory> getProductCategories();
    void updateProductStatus(int id, int state);

}
