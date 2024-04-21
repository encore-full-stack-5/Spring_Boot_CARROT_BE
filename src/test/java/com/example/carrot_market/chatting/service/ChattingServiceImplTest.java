package com.example.carrot_market.chatting.service;

import com.example.carrot_market.chatting.domain.Chat;
import com.example.carrot_market.chatting.domain.ChatRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(properties = "spring.profiles.active=dev")
class ChattingServiceImplTest {

    @Autowired ChattingService chattingService;

    @Test
    void getChatRoomListByUserId() {
        List<ChatRoom> chatRooms = chattingService.getChatRoomListByUserId(20);
        for (ChatRoom chatRoom : chatRooms) {
            System.out.println(chatRoom.toString());
        }
    }

    @Test
    void getChatRoom() {
        ChatRoom chatRoom = chattingService.getChatRoom(1);
        System.out.println(chatRoom.toString());
    }

    @Test
    void getChatListByRoomId() {
        List<Chat> chatList = chattingService.getChatListByRoomId(1);
        for (Chat chat : chatList) {
            System.out.println(chat.toString());
        }
    }
}