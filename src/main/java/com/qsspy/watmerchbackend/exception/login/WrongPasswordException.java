package com.qsspy.watmerchbackend.exception.login;


public class WrongPasswordException extends LoginException {

    public WrongPasswordException(String message) {
        super(message);
    }
}
