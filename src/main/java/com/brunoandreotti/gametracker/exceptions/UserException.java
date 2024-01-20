package com.brunoandreotti.gametracker.exceptions;


import lombok.Getter;

@Getter
public class UserException extends RuntimeException{


    public UserException(String message) {
        super(message);
    }
}
