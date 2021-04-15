package com.qsspy.watmerchbackend.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String username) {
        super(username);
    }
}
