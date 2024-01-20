package com.brunoandreotti.gametracker.exceptions;


import lombok.Getter;

@Getter
public class JWTException extends RuntimeException{


    public JWTException(String message) {
        super(message);
    }
    public JWTException(String message, Exception ex) {
        super(message);
    }
}
