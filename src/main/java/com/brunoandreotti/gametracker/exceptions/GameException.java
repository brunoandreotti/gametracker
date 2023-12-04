package com.brunoandreotti.gametracker.exceptions;


import lombok.Getter;

@Getter
public class GameException extends RuntimeException{


    public GameException(String message) {
        super(message);
    }
}
