package com.brunoandreotti.gametracker.domain.repositories;

import com.brunoandreotti.gametracker.domain.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Boolean existsByName(String name);
}
