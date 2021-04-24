package com.qsspy.watmerchbackend.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Base64;

@Data
public class UserAndPasswordModel implements Serializable {

    public static UserAndPasswordModel basicAuthBase64Decode(String encodedString) {

        encodedString = encodedString.replace("Basic ","");
        String decodedString = new String(Base64.getDecoder().decode(encodedString));
        String[] creds = decodedString.split(":");
        return new UserAndPasswordModel(creds[0],creds[1]);

    }

    private String username;
    private String password;

    public UserAndPasswordModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
