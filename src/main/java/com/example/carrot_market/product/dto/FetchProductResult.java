package com.example.carrot_market.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class FetchProductResult {
    List<FetchProductDto> result;
    int lastId;
}
