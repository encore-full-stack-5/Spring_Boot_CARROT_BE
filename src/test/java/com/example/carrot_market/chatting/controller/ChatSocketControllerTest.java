package com.example.carrot_market.chatting.controller;

import com.example.carrot_market.chatting.dto.CreateChatDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class ChatSocketControllerTest {

    @Test
    public void testSendMessage() throws Exception {
        // 테스트에 필요한 StompSession과 StompSessionHandler 생성
        StompSessionHandler sessionHandler = mock(StompSessionHandler.class);
        StompSession session = mock(StompSession.class);
        int roomId = 1;

        // 서버에 메시지를 보내는 로직 구현
        CreateChatDto chatDto = new CreateChatDto(20,roomId,"Hello, World!",1);

        // 해당 roomId와 메시지를 보내는 로직 호출
        session.send("/app/room/" + roomId, chatDto);

        // 로그 및 반환 값 검증
        verify(sessionHandler, times(1)).handleFrame(any(), any());
        // 추가적인 상태 검증이 필요할 경우 구현
    }
}
