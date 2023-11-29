package com.brunoandreotti.gametracker.controllers;


import com.brunoandreotti.gametracker.dtos.game.GameRequestDTO;
import com.brunoandreotti.gametracker.dtos.game.GameResponseDTO;
import com.brunoandreotti.gametracker.services.GameService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameResponseDTO> create(@RequestBody GameRequestDTO gameData) {

        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.create(gameData));
    }
}
