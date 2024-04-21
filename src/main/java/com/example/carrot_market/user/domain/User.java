package com.example.carrot_market.user.domain;

import com.example.carrot_market.area.domain.model.UserArea;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class User {
    int id;
    String nickname;
    String phone;
    Timestamp createdAt;
    double userScore;
    String profileImage;
    Timestamp deletedAt;
}
