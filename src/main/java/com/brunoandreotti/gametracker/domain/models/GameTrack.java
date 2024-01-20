package com.brunoandreotti.gametracker.domain.models;

import com.brunoandreotti.gametracker.domain.models.user.User;
import com.brunoandreotti.gametracker.dtos.gametrack.GameTrackRequestDTO;
import com.brunoandreotti.gametracker.enums.GameStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_game_track")
@Data
@NoArgsConstructor
public class GameTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    GameStatus status = GameStatus.TO_PLAY;

    @Column
    Double rate;

    @Column
    Double hoursPlayed;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public GameTrack(GameTrackRequestDTO gameTrack) {
        this.hoursPlayed = gameTrack.getHoursPlayed();
        this.rate = gameTrack.getRate();
        this.status = gameTrack.getStatus();
    }
}
