package com.example.carrot_market.product.dto;

import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.user.domain.User;

public record InsertLikeCountRequestDto(int productId, int userId) {

}
