package com.brunoandreotti.gametracker.domain.models;

import com.brunoandreotti.gametracker.enums.GameStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_game_track")
@Data
public class GameTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    GameStatus status = GameStatus.TO_PLAY;

    @Column
    Integer rate;

    @Column
    Double hoursPlayed;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
}
