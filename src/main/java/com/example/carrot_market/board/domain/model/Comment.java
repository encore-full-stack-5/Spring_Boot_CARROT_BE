package com.example.carrot_market.board.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
public class Comment {
    private int id;
    private int boardId;
    private int userId;
    private int areaId;
    private Timestamp createdAt;
    private int likeCount;
    private String content;
}
