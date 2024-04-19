package com.example.carrot_market.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Product {
// # id, seller_id, selling_area_id, category_id, is_negotiation, created_at, state, view_count, title, content, refreshed_at, price, deleted_at, chatting_count, like_count
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