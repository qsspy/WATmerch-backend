package com.qsspy.watmerchbackend.service;

import com.github.javafaker.Faker;
import com.qsspy.watmerchbackend.model.WebSocketMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class WebSocketService implements IWebSocketService {

    Set<String> privateListeners = new HashSet<>();

    private final Faker faker = new Faker();

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void addPrivateSubscriber(String sessionID) {
        privateListeners.add(sessionID);
    }

    @Override
    public void removePrivateSubscriber(String sessionID) {
        privateListeners.remove(sessionID);
    }

    @Scheduled(fixedDelay = 5000)
    @Override
    public void sendPublicNotification() {
        log.info("Emiting data...");
        simpMessagingTemplate.convertAndSend("/topic/public", faker.chuckNorris().fact());
    }

    @Override
    public void sendPrivateNotifications() {

    }

    @Override
    public WebSocketMessageModel getBotAnswer(String question) {
        return new WebSocketMessageModel(null,"WATmerch",faker.chuckNorris().fact());
    }
}
