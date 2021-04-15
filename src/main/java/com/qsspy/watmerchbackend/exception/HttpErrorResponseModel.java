package com.qsspy.watmerchbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HttpErrorResponseModel {

    private int statusCode;
    private String message;
    private String details;
}
