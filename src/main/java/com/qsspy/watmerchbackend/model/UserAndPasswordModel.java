package com.qsspy.watmerchbackend.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAndPasswordModel implements Serializable {

    private String username;
    private String password;
}
