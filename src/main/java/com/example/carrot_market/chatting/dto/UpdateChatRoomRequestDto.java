package com.example.carrot_market.chatting.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateChatRoomRequestDto {
    private int sellerLastReadChatId;
    private int customerLastReadChatId;
    private boolean isExistSeller;
    private boolean isExistCustomer;
    private String lastChat;
}