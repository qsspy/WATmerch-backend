package com.qsspy.watmerchbackend.exception.login;

import com.qsspy.watmerchbackend.exception.login.LoginException;

public class UserNotFoundException extends LoginException {

    public UserNotFoundException(String username) {
        super(username);
    }
}
