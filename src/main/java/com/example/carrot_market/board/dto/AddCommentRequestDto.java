package com.example.carrot_market.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class AddCommentRequestDto {
    private int id;
    private int boardId;
    private int userId;
    private int areaId;
    private Timestamp createdAt;
    private int likeCount;
    private String content;
}