package com.example.carrot_market.chatting.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateChatRoomRequestDto {
    @NotNull(message = "productId 파라미터가 누락 되었습니다.")
    private int productId;
    @NotNull(message = "sellerId 파라미터가 누락 되었습니다.")
    private int sellerId;
    @NotNull(message = "userId 파라미터가 누락 되었습니다.")
    private int userId;
}
