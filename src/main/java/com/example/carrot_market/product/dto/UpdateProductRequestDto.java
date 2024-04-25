package com.example.carrot_market.product.dto;
import com.example.carrot_market.product.domain.Product;
import com.example.carrot_market.user.domain.User;
import lombok.Data;
import lombok.Getter;

public record UpdateProductRequestDto (
    String title,
    String content,
    int price,
    int isNegotiation,
    int state
) {
    public Product toEntity(int id) {
        return Product.builder()
                .id(id)
                .title(title)
                .content(content)
                .price(price)
                .isNegotiation(isNegotiation)
                .state(state)
                .build();
    }
}
