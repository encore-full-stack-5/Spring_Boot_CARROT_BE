package com.example.carrot_market.chatting.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateChatRoomRequestDto {
    private int id;
    private int sellerLastReadChatId;
    private int customerLastReadChatId;
    private Integer isExistSeller;
    private Integer isExistCustomer;
    private String lastChat;
}