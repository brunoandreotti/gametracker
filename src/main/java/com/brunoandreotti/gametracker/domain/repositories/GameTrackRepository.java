package com.brunoandreotti.gametracker.domain.repositories;

import com.brunoandreotti.gametracker.domain.models.Game;
import com.brunoandreotti.gametracker.domain.models.GameTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameTrackRepository extends JpaRepository<GameTrack, Long> {

    List<GameTrack> findGameTrackByGame(Game game);
}
