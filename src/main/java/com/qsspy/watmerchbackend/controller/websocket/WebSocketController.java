package com.qsspy.watmerchbackend.controller.websocket;

import com.qsspy.watmerchbackend.model.WebSocketMessageModel;
import com.qsspy.watmerchbackend.service.IWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {

    private final IWebSocketService webSocketService;

    public WebSocketController(IWebSocketService supportBotService) {
        this.webSocketService = supportBotService;
    }

    @MessageMapping("/support")
    public void reply(
            @Payload WebSocketMessageModel message) {
    }

    @MessageMapping("/public")
    public void registerToPublic(StompHeaderAccessor accessor) {

    }
}
