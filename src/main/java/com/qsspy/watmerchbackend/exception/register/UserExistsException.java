package com.qsspy.watmerchbackend.exception.register;

public class UserExistsException extends RegisterException{

    public UserExistsException(String message) {
        super(message);
    }
}
