package com.example.carrot_market.product.repository;

import com.example.carrot_market.product.domain.ProductImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.awt.*;
import java.util.List;

@Mapper
public interface ImageMapper {
    void insertProductImage(ProductImage productImage);
    List<ProductImage> findImageByTypeAndTypeId(int type, int typeId);
    List<ProductImage> findImagesByProductIds(@Param("productIds") List<Integer> productIds);

    List<ProductImage> findImagesByProductIdOne(@Param("productId") int id) ;

}
