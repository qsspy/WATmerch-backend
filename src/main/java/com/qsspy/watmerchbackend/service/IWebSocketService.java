package com.qsspy.watmerchbackend.service;

public interface IWebSocketService {

    void addPrivateSubscriber(String sessionID, String name);
    void removePrivateSubscriber(String sessionID);
    void sendPublicNotification();
    void sendPrivateNotifications();
}
