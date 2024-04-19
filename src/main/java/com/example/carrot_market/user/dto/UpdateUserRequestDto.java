package com.example.carrot_market.user.dto;

import com.example.carrot_market.user.domain.User;

import java.sql.Timestamp;
public record UpdateUserRequestDto(String nickname, String phone, String profileImage) {
    public User toEntity() {
        return User.builder()
                .nickname(nickname)
                .phone(phone)
                .profileImage(profileImage)
                .build();
    }
    public User toEntity(int id) {
        return User.builder()
                .id(id)
                .nickname(nickname)
                .phone(phone)
                .profileImage(profileImage)
                .build();
    }
}
