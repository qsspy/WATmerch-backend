package com.qsspy.watmerchbackend.service;

import com.github.javafaker.Faker;
import com.qsspy.watmerchbackend.model.WebSocketMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WebSocketService implements IWebSocketService {

    Map<String, String> privateListeners = new HashMap<>();

    private final Faker faker = new Faker();

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    @Scheduled(fixedDelay = 20000)
    public void sendPrivateNotifications() {
        privateListeners.forEach((listener,name)->{
            log.info("Sending notification to " + name + "...");
            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
            headerAccessor.setSessionId(listener);
            headerAccessor.setLeaveMutable(true);
            simpMessagingTemplate.convertAndSendToUser(
                    listener,
                    "/queue/private",
                    new WebSocketMessageModel("WATmerch", name + ", here's some Witcher 3 quote for you : " + faker.witcher().quote()),
                    headerAccessor.getMessageHeaders()
            );
        }
        );
    }

    @Scheduled(fixedDelay = 30000)
    @Override
    public void sendPublicNotification() {
        log.info("Emiting public notification...");
        simpMessagingTemplate.convertAndSend("/topic/public", new WebSocketMessageModel("WATmerch", faker.chuckNorris().fact()));
    }

    @Override
    public void addPrivateSubscriber(String sessionID, String name) {
        privateListeners.put(sessionID, name);
    }

    @Override
    public void removePrivateSubscriber(String sessionID) {

        privateListeners.remove(sessionID);
    }

    @EventListener
    public void sessionDisconnectionHandler(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        log.info("Disconnecting " + sessionId + "!");
        privateListeners.remove(sessionId);
    }
}
