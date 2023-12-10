package com.brunoandreotti.gametracker.dtos.gametrack;

import com.brunoandreotti.gametracker.domain.models.Game;
import com.brunoandreotti.gametracker.domain.models.GameTrack;
import com.brunoandreotti.gametracker.enums.GameStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameTrackResponseDTO {

    Long id;

    GameStatus status ;

    Integer rate;

    Double hoursPlayed;

    Game game;

     public GameTrackResponseDTO(GameTrack gameTrack) {
         this.id = gameTrack.getId();
         this.status = gameTrack.getStatus();
         this.rate = gameTrack.getRate();
         this.hoursPlayed = gameTrack.getHoursPlayed();
         this.game = gameTrack.getGame();
     }
}
