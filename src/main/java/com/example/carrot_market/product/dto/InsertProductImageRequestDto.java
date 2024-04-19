package com.example.carrot_market.product.dto;

public record InsertProductImageRequestDto(
        int productId,
        String image,
        int type
) {

}
