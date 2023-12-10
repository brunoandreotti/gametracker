package com.brunoandreotti.gametracker.controllers;

import com.brunoandreotti.gametracker.domain.models.GameTrack;
import com.brunoandreotti.gametracker.dtos.game.GameRequestDTO;
import com.brunoandreotti.gametracker.dtos.game.GameResponseDTO;
import com.brunoandreotti.gametracker.dtos.gametrack.GameTrackRequestDTO;
import com.brunoandreotti.gametracker.dtos.gametrack.GameTrackResponseDTO;
import com.brunoandreotti.gametracker.services.GameTrackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/gametrack", produces = {"application/json"})
@Tag(name = "open-api")
public class GameTrackController {

    private final GameTrackService gameTrackService;

    public GameTrackController(GameTrackService gameTrackService) {
        this.gameTrackService = gameTrackService;
    }

    @Operation(summary = "Realiza o cadastro de um game track", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game track cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Jogo não encontrado")
    })
    @PostMapping()
    public ResponseEntity<GameTrackResponseDTO> create(@Valid @RequestBody GameTrackRequestDTO gameTrackData) {

        return ResponseEntity.status(HttpStatus.CREATED).body(gameTrackService.create(gameTrackData));
    }

    @Operation(summary = "Retorna todos os game tracks", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game track retornados com sucesso"),

    })
    @GetMapping()
    public ResponseEntity<List<GameTrackResponseDTO>> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(gameTrackService.findAll());
    }

    @Operation(summary = "Retorna game tracks baseado no nome do jogo", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game track retornados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Jogo não encontrado")

    })

    @GetMapping("/{name}")
    public ResponseEntity<List<GameTrackResponseDTO>> findByGameName(@PathVariable String name) {

        return ResponseEntity.status(HttpStatus.OK).body(gameTrackService.findByGameName(name));
    }

    @Operation(summary = "Realiza a deleção de um game track pelo seu id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game track deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Game track com id informado não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByName(@PathVariable Long id) {

        gameTrackService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Realiza a atualização de um game track pelo seu id", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game track atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Game track com id informado não encontrado")
    })
    @PutMapping ("/{id}")
    public ResponseEntity<GameTrackResponseDTO> deleteByName(@PathVariable Long id, @Valid @RequestBody GameTrackRequestDTO gameTrackData) {

        return ResponseEntity.status(HttpStatus.OK).body(gameTrackService.updateById(id, gameTrackData));
    }


}
