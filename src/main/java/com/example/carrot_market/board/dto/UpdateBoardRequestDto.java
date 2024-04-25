package com.example.carrot_market.board.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class UpdateBoardRequestDto {
    private int id;
    private String content;
    private String category;
    private String title;
    private Timestamp updateAt;
}
