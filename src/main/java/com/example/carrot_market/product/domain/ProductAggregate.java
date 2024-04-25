package com.example.carrot_market.product.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
//@SuperBuilder
public class ProductAggregate extends ProductResponseDto {
    private final boolean isLike;
    private final int likeCount;
    private final List<ProductImage> images;

    // Constructor that accepts a Product and additional fields
    public ProductAggregate(ProductResponseDto product, boolean isLike, int likeCount, List<ProductImage> images) {
        super(product.getId(), product.getSellerId(), product.getSellingAreaId(), product.getCategoryId(), product.getIsNegotiation(), product.getCreatedAt(), product.getState(), product.getViewCount(), product.getTitle(), product.getContent(), product.getRefreshedAt(), product.getPrice(), product.getDeletedAt(), product.getChattingCount(), product.getLikeCount(), product.getAddress());
        this.isLike = isLike;
        this.likeCount = likeCount;
        this.images = images;
    }
}