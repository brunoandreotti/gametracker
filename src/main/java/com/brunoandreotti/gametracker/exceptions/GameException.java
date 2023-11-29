package com.brunoandreotti.gametracker.exceptions;

import lombok.Data;
import lombok.Getter;

@Getter
public class GameException extends RuntimeException{


    public GameException(String message) {
        super(message);
    }
}
