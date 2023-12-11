package com.brunoandreotti.gametracker.controllers;


import com.brunoandreotti.gametracker.dtos.ApiResponseDTO;
import com.brunoandreotti.gametracker.dtos.game.GameRequestDTO;
import com.brunoandreotti.gametracker.dtos.game.GameResponseDTO;
import com.brunoandreotti.gametracker.services.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ApiResponseDTO<GameResponseDTO>> create(@Valid @RequestBody GameRequestDTO gameData) {


        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<>(true, Integer.toString(HttpStatus.CREATED.value()), gameService.create(gameData)));
    }

    @Operation(summary = "Realiza a busca de um jogo pelo seu nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogo encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Jogo com nome informado não encontrado")
    })
    @GetMapping("/{name}")
    public ResponseEntity<ApiResponseDTO<GameResponseDTO>> findByName(@PathVariable String name) {

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>(true, Integer.toString(HttpStatus.OK.value()), gameService.findByName(name)));
        
    }

    @Operation(summary = "Realiza a busca do nome de jogos cdastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nome encontrado com sucesso"),

    })
    @GetMapping("/names/{name}")
    public ResponseEntity<ApiResponseDTO<List<String>>> findGamesName(@PathVariable(required = false) String name) {



        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>(true, Integer.toString(HttpStatus.OK.value()), gameService.findGamesName(name)));
    }

    @Operation(summary = "Realiza a busca de todos os nomes dos jogos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nomes encontrado com sucesso"),

    })
    @GetMapping("/names")
    public ResponseEntity<ApiResponseDTO<List<String>>> findAllGamesName() {

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>(true, Integer.toString(HttpStatus.OK.value()), gameService.findAllGamesName()));

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
