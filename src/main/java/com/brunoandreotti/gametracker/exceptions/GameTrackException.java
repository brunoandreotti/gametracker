package com.brunoandreotti.gametracker.exceptions;

import lombok.Getter;

@Getter
public class GameTrackException extends RuntimeException{

    public GameTrackException(String message) {
        super(message);
    }
}
