package com.example.carrot_market.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponseDto {
    private int id;
    private int sellerId;
    private int sellingAreaId;
    private int categoryId;
    private int isNegotiation;
    private String createdAt;
    private int state;
    private int viewCount;
    private String title;
    private String content;
    private String refreshedAt;
    private int price;
    private String deletedAt;
    private int chattingCount;
    private int likeCount;
    private String address;
}