package com.qsspy.watmerchbackend.model;


public class WebSocketMessageModel {

    private String name;
    private String message;

    public WebSocketMessageModel(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public WebSocketMessageModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
