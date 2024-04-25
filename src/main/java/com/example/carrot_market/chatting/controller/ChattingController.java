package com.example.carrot_market.chatting.controller;

import com.example.carrot_market.chatting.domain.Chat;
import com.example.carrot_market.chatting.domain.ChatRoom;
import com.example.carrot_market.chatting.domain.ChatRoomAggregate;
import com.example.carrot_market.chatting.dto.CreateChatDto;
import com.example.carrot_market.chatting.dto.CreateChatRoomRequestDto;
import com.example.carrot_market.chatting.dto.UpdateChatRoomRequestDto;
import com.example.carrot_market.chatting.service.ChattingService;
import com.example.carrot_market.core.base.BaseResponseEntity;
import com.example.carrot_market.core.util.LogUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/v1/chat")
@RequiredArgsConstructor
public class ChattingController {

    @Autowired
    private final ChattingService chattingService;

    @PostMapping("/room")
    public ResponseEntity<BaseResponseEntity<ChatRoom>> createChatRoom(@RequestBody CreateChatRoomRequestDto createChatRoomRequestDto) {
        return BaseResponseEntity.ok(chattingService.createChatRoom(createChatRoomRequestDto), "success");
    }

    @PostMapping("/chat")
    public ResponseEntity<BaseResponseEntity<Chat>> createMessage(@RequestBody CreateChatDto createChatDto) {
        return BaseResponseEntity.ok(chattingService.createMessage(createChatDto), "success");
    }

    @GetMapping("/users/{userId}/rooms")
    public ResponseEntity<BaseResponseEntity<List<ChatRoomAggregate>>> getRoomListByUserId(@PathVariable("userId") int userId) {
        return BaseResponseEntity.ok(chattingService.getChatRoomListByUserId(userId), "success");
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<BaseResponseEntity<ChatRoom>> getRoom(@PathVariable("roomId") int roomId) {
        return BaseResponseEntity.ok(chattingService.getChatRoom(roomId), "success");
    }

    @GetMapping("/room/{roomId}/chats")
    public ResponseEntity<BaseResponseEntity<List<Chat>>> getChatsByRoomId(@PathVariable("roomId") int roomId) {
        LogUtil.logPretty("roomId: " + roomId);
        return BaseResponseEntity.ok(chattingService.getChatListByRoomId(roomId), "success");
    }

    @PatchMapping("/room/{id}")
    public ResponseEntity<BaseResponseEntity<?>> updateChatRoom(@PathVariable("id") int id, @RequestBody UpdateChatRoomRequestDto updateDto) {
        chattingService.updateChatRoom(updateDto);
        return BaseResponseEntity.ok("success");
    }


}
