package com.example.carrot_market.chatting.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
@Data
public class Chat {
    private int id;
    private int chatRoomId;
    private int senderUserId;
    private Timestamp sentAt;
    private String message;
    private int chatType;

}


