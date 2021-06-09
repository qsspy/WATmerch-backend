package com.qsspy.watmerchbackend.service;

import com.qsspy.watmerchbackend.model.WebSocketMessageModel;

public interface IWebSocketService {

    void addPrivateSubscriber(String sessionID);
    void removePrivateSubscriber(String sessionID);
    void sendPublicNotification();
    void sendPrivateNotifications();

    WebSocketMessageModel getBotAnswer(String question);

}
