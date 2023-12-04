package com.brunoandreotti.gametracker.controllers;


import com.brunoandreotti.gametracker.dtos.game.GameRequestDTO;
import com.brunoandreotti.gametracker.dtos.game.GameResponseDTO;
import com.brunoandreotti.gametracker.services.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Threshold;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/games", produces = {"application/json"})
@Tag(name = "open-api")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(summary = "Realiza o cadastro de um jogo", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Jogo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Jogo já cadastrado")
    })
    @PostMapping()
    public ResponseEntity<GameResponseDTO> create(@RequestBody GameRequestDTO gameData) {

        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.create(gameData));
    }

    @Operation(summary = "Realiza a busca de um jogo pelo seu nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogo encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Jogo com nome informado não encontrado")
    })
    @GetMapping("/{name}")
    public ResponseEntity<GameResponseDTO> findByName(@PathVariable String name) {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.findByName(name));
    }

    @Operation(summary = "Realiza a busca do nome de jogos cdastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nome encontrado com sucesso"),

    })
    @GetMapping("/names/{name}")
    public ResponseEntity<List<String>> findGamesName(@PathVariable(required = false) String name) {


        return ResponseEntity.status(HttpStatus.OK).body(gameService.findGamesName(name));
    }

    @Operation(summary = "Realiza a busca de todos os nomes dos jogos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nomes encontrado com sucesso"),

    })
    @GetMapping("/names")
    public ResponseEntity<List<String>> findAllGamesName() {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.findAllGamesName());
    }

    @Operation(summary = "Realiza a deleção de um jogo pelo seu nome", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogo deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Jogo com nome informado não encontrado")
    })
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteByName(@PathVariable String name) {

        gameService.deleteByName(name);

       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
