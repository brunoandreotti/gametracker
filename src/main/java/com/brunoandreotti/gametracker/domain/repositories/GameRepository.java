package com.brunoandreotti.gametracker.domain.repositories;

import com.brunoandreotti.gametracker.domain.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Boolean existsByNameIgnoreCase(String name);

    Optional<Game> findByName(String name);

    List<Game> findByNameStartingWithIgnoreCase(String name);
}
