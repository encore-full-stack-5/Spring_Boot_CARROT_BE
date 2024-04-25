package com.example.carrot_market.board.dto;

import java.sql.Timestamp;

public record getDetailBoardResultDto(
        int userId,
        String nickname,
        String userImage,
        int areaId,
        String areaName,
        String category,
        int boardId,
        Timestamp createdAt,
        String content,
        String title,
        int viewCount,
        int commentCount,
        int likeCount
) {
}
