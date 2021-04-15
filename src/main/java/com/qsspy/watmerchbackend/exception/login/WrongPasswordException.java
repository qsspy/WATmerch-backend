package com.qsspy.watmerchbackend.exception.login;

import com.qsspy.watmerchbackend.exception.login.LoginException;

public class WrongPasswordException extends LoginException {

    public WrongPasswordException(String message) {
        super(message);
    }
}
