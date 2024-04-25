package com.example.carrot_market.product.domain;

import com.example.carrot_market.area.domain.model.Area;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
//@SuperBuilder
public class ProductAggregate extends Product {
    private final boolean isLike;
    private final int likeCount;
    private final List<ProductImage> images;
    private final String nickname;
    private final Area area;
    private final double userScore;
    private final String categoryName;


    // Constructor that accepts a Product and additional fields
    public ProductAggregate(Product product, boolean isLike, int likeCount, List<ProductImage> images, String nickname, Area area, double userScore, String categoryName) {
        super(product.getId(), product.getSellerId(), product.getSellingAreaId(), product.getCategoryId(), product.getIsNegotiation(), product.getCreatedAt(), product.getState(), product.getViewCount(), product.getTitle(), product.getContent(), product.getRefreshedAt(), product.getPrice(), product.getDeletedAt(), product.getChattingCount(), product.getLikeCount());
        this.isLike = isLike;
        this.likeCount = likeCount;
        this.images = images;
        this.nickname = nickname;
        this.area = area;
        this.userScore = userScore;
        this.categoryName = categoryName;
    }
}