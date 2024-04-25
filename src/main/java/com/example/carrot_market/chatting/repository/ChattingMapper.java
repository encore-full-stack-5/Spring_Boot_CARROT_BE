package com.example.carrot_market.chatting.repository;

import com.example.carrot_market.chatting.domain.Chat;
import com.example.carrot_market.chatting.domain.ChatRoom;
import com.example.carrot_market.chatting.domain.ChatRoomAggregate;
import com.example.carrot_market.chatting.dto.UpdateChatRoomRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ChattingMapper {
    void createChatRoom(ChatRoom room);
    void createMessage(Chat chat);

    List<ChatRoom> findChatRoomListByUserId(@Param("userId") int userId);
    List<ChatRoomAggregate> findChatRoomAggregateListByUserId(@Param("userId") int userId);
    Optional<ChatRoom> findChatRoomByRoomId(@Param("roomId") int roomId);
    List<Chat> findChatListByRoomId(@Param("roomId") int roomId);
    void updateLastReadMessage(int roomId, int userId, int chatId);
    void updateChatRoom(UpdateChatRoomRequestDto updateChatRoomRequestDto);
    void deleteChatRoom(int roomId);
    void exitChatRoom(int roomId, int userId);
}
