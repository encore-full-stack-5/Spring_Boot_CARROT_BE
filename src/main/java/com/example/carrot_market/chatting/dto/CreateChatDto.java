package com.example.carrot_market.chatting.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateChatDto {
    @NotNull(message = "userId 파라미터가 누락 되었습니다.")
    private int userId;
    @NotNull(message = "roomId 파라미터가 누락 되었습니다.")
    private int roomId;
    @NotNull(message = "message 파라미터가 누락 되었습니다.")
    private String message;
    @NotNull(message = "chatType 파라미터가 누락 되었습니다.")
    private int chatType;
}
