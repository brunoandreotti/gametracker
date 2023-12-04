package com.brunoandreotti.gametracker.domain.repositories;

import com.brunoandreotti.gametracker.domain.models.GameTrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameTrackRepository extends JpaRepository<GameTrack, Long> {
}
