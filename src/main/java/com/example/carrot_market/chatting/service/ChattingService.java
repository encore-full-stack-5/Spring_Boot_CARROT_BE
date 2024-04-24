package com.example.carrot_market.chatting.service;


import com.example.carrot_market.chatting.domain.Chat;
import com.example.carrot_market.chatting.domain.ChatRoom;
import com.example.carrot_market.chatting.domain.ChatRoomAggregate;
import com.example.carrot_market.chatting.dto.CreateChatDto;
import com.example.carrot_market.chatting.dto.CreateChatRoomRequestDto;
import com.example.carrot_market.chatting.dto.UpdateChatRoomRequestDto;

import java.util.List;

// 웹소켓
public interface ChattingService {

    ChatRoom createChatRoom(CreateChatRoomRequestDto createChatRoomRequestDto);
    Chat createMessage(CreateChatDto createChatDto);

    void updateChatRoom(UpdateChatRoomRequestDto updateChatRoomRequestDto);
    void deleteChattingRoom(int roomId);
    void exitChattingRoom(int roomId, int userId);

    // GET
    List<ChatRoomAggregate> getChatRoomListByUserId(int userId);
    ChatRoom getChatRoom(int roomId);
    List<Chat> getChatListByRoomId(int roomId);

}
