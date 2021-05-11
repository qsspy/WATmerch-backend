package com.qsspy.watmerchbackend.service;

import com.github.javafaker.Faker;
import com.qsspy.watmerchbackend.model.WebSocketMessageModel;
import org.springframework.stereotype.Service;

@Service
public class SupportBotService implements ISupportBotService{

    private final Faker faker = new Faker();

    @Override
    public WebSocketMessageModel getBotAnswer(String question) {
        return new WebSocketMessageModel(null,"WATmerch",faker.chuckNorris().fact());
    }
}
