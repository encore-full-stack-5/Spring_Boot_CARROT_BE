package com.example.carrot_market.chatting.service;

import com.example.carrot_market.chatting.domain.Chat;
import com.example.carrot_market.chatting.domain.ChatRoom;
import com.example.carrot_market.chatting.domain.ChatType;
import com.example.carrot_market.chatting.dto.CreateChatDto;
import com.example.carrot_market.chatting.dto.CreateChatRoomRequestDto;
import com.example.carrot_market.chatting.dto.UpdateChatRoomRequestDto;
import com.example.carrot_market.chatting.repository.ChattingMapper;
import com.example.carrot_market.core.error.CommonError;
import com.example.carrot_market.core.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingServiceImpl implements ChattingService {

    @Autowired
    private final ChattingMapper chatRoomMapper;

    @Override
    @Transactional
    public ChatRoom createChatRoom(CreateChatRoomRequestDto createChatRoomRequestDto) {
        ChatRoom room = ChatRoom.builder()
                .id(0)
                .productId(createChatRoomRequestDto.getProductId())
                .sellerId(createChatRoomRequestDto.getSellerId())
                .customerId(createChatRoomRequestDto.getUserId())
                .lastChat("채팅방이 생성되었습니다.")
                .build();
        chatRoomMapper.createChatRoom(room);

        createMessage(new CreateChatDto(1, room.getId(), "채팅방이 생성되었습니다.", ChatType.SYSTEM.getValue()));
        return room;
    }

    @Override
    public Chat createMessage(CreateChatDto createChatDto) {
        Chat chat = Chat.builder()
                .id(0)
                .chatRoomId(createChatDto.getRoomId())
                .senderUserId(createChatDto.getUserId())
                .message(createChatDto.getMessage())
                .chatType(createChatDto.getChatType())
                .sentAt(TimeUtil.getCurrentTimestamp())
                .build();
        chatRoomMapper.createMessage(chat);
        return chat;
    }

    @Override
    public List<ChatRoom> getChatRoomListByUserId(int userId) {
        return chatRoomMapper.findChatRoomListByUserId(userId);
    }

    @Override
    public ChatRoom getChatRoom(int roomId) {
        return chatRoomMapper.findChatRoomByRoomId(roomId).orElseThrow(() -> new CommonError.Unexpected.IllegalArgumentException("채팅방이 존재하지 않습니다."));
    }

    @Override
    public List<Chat> getChatListByRoomId(int roomId) {
        List<Chat> chats = chatRoomMapper.findChatListByRoomId(roomId);
        if (chats.isEmpty()) {
            throw new CommonError.Unexpected.IllegalArgumentException("채팅이 존재하지 않습니다.");
        }
        return chats;
    }

    @Override
    public void updateChatRoom(UpdateChatRoomRequestDto updateChatRoomRequestDto) {
        chatRoomMapper.updateChatRoom(updateChatRoomRequestDto);
    }

    @Override
    public void deleteChattingRoom(int roomId) {

    }

    @Override
    public void exitChattingRoom(int roomId, int userId) {

    }

}
