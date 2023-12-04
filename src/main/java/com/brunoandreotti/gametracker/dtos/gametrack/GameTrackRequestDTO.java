package com.brunoandreotti.gametracker.dtos.gametrack;

import com.brunoandreotti.gametracker.enums.GameStatus;
import lombok.Data;

@Data
public class GameTrackRequestDTO {

    GameStatus status;

    Integer rate;

    Double hoursPlayed;

    Long gameId;
}
