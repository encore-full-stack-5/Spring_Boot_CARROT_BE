package com.example.carrot_market.product.dto;

import com.example.carrot_market.product.domain.FetchProductDto;
import com.example.carrot_market.product.domain.ProductAggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class FetchProductResultDto {
    List<FetchProductDto> result;
    int lastId;
}
