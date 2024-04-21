package com.example.carrot_market.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Product {
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
}