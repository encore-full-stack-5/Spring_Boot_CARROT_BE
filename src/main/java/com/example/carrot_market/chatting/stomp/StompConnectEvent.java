package com.example.carrot_market.chatting.stomp;

import com.example.carrot_market.chatting.service.ChattingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

//@Component
//public class StompConnectEvent implements ApplicationListener<SessionConnectEvent> {
//
//    @Autowired
//    private ChattingService chattingService;
//
//    @Override
//    public void onApplicationEvent(SessionConnectEvent event) {
//        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
//        String userId = sha.getUser().getName();
//
//        Long lastReadMessageId = ...;
//        messageService.updateLastReadMessage(userId, lastReadMessageId);
//    }
//}