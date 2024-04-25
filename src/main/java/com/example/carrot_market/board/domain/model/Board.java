package com.example.carrot_market.board.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class Board {
    private int id;
    private int userId;
    private int areaId;
    private String createdAt;
    private String content;
    private int category;
    private String title;
    private int viewCount;
    private String updateAt;
    private int commentCount;
    private int likeCount;
}
