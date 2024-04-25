package com.example.carrot_market.board.dto;

public record AddBoardRequestDto(
    int userId,
    int areaId,
    String content,
    String title,
    int category
) {
}
