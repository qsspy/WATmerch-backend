package com.qsspy.watmerchbackend.exception.login;

public class UserNotFoundException extends LoginException {

    public UserNotFoundException(String username) {
        super(username);
    }
}
