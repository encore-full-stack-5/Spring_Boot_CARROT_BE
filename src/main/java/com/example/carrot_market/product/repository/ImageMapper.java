package com.example.carrot_market.product.repository;

import com.example.carrot_market.product.domain.ProductImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
    void insertProductImage(ProductImage productImage);

}
