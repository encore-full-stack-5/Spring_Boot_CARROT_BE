package com.example.carrot_market.chatting.controller;

import com.example.carrot_market.chatting.domain.Chat;
import com.example.carrot_market.chatting.dto.CreateChatDto;
import com.example.carrot_market.chatting.service.ChattingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class ChatSocketController {

    @Autowired
    private final ChattingService chattingService;

    @MessageMapping("/{roomId}")
    @SendTo("/sub/room/{roomId}")
    public Chat sendMessage(@DestinationVariable("roomId") int roomId, @Payload CreateChatDto chatDto) {
        log.info("Received message for room {}: {}", roomId, chatDto);
        try {
            Chat response = chattingService.createMessage(chatDto);
            return response;
        } catch (Exception e) {
            log.error("Error processing message: ", e);
            throw e; // Rethrow or handle as appropriate
        }
    }
}
