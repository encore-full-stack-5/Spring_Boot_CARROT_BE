package com.example.carrot_market.chatting.dto;

import com.example.carrot_market.chatting.domain.Chat;
import com.example.carrot_market.core.util.TimeUtil;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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


    public Chat toDomain() {
        return Chat.builder()
                .id(0)
                .chatRoomId(this.getRoomId())
                .senderUserId(this.getUserId())
                .message(this.getMessage())
                .chatType(this.getChatType())
                .sentAt(TimeUtil.getCurrentTimestamp())
                .build();
    }
}
