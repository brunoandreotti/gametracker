package com.brunoandreotti.gametracker.controllers;


import com.brunoandreotti.gametracker.dtos.game.GameRequestDTO;
import com.brunoandreotti.gametracker.dtos.game.GameResponseDTO;
import com.brunoandreotti.gametracker.services.GameService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{name}")
    public ResponseEntity<GameResponseDTO> findByName(@PathVariable String name) {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.findByName(name));
    }

    @GetMapping("/names/{name}")
    public ResponseEntity<List<String>> findGamesName(@PathVariable(required = false) String name) {


        return ResponseEntity.status(HttpStatus.OK).body(gameService.findGamesName(name));
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> findAllGamesName() {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.findAllGamesName());
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteByName(@PathVariable String name) {

        gameService.deleteByName(name);

       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
