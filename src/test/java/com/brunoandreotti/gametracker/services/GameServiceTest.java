package com.brunoandreotti.gametracker.services;

import com.brunoandreotti.gametracker.domain.models.Game;
import com.brunoandreotti.gametracker.domain.repositories.GameRepository;
import com.brunoandreotti.gametracker.dtos.game.GameRequestDTO;
import com.brunoandreotti.gametracker.dtos.game.GameResponseDTO;
import com.brunoandreotti.gametracker.exceptions.GameException;
import com.brunoandreotti.gametracker.utils.GameMapper;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Test
    public void createGame_WithValidData_ReturnsGameResponse() {

        GameRequestDTO gameRequestDTO = new GameRequestDTO("Jogo1", "https");
        Game gameWithId = new Game(1L, "Jogo1", 5, "https");


        Mockito.when(gameRepository.existsByNameIgnoreCase(gameRequestDTO.getName())).thenReturn(false);
        Mockito.when(gameRepository.save(GameMapper.fromGameRequestToGame(gameRequestDTO))).thenReturn(gameWithId);


        GameResponseDTO gameResponse = gameService.create(gameRequestDTO);


        Assertions.assertThat(gameResponse).hasFieldOrProperty("id");
        Assertions.assertThat(gameResponse.getName()).isEqualTo(gameRequestDTO.getName());
        Assertions.assertThat(gameResponse.getImageUrl()).isEqualTo(gameRequestDTO.getImageUrl());
    }

    @Test
    public void createGame_WithExistingValidData_ReturnsExistsGameException() {

        GameRequestDTO gameRequestDTO = new GameRequestDTO("Jogo1", "https");
        Game gameWithId = new Game(1L, "Jogo1", 5, "https");

        Mockito.when(gameRepository.existsByNameIgnoreCase(gameRequestDTO.getName())).thenReturn(true);


        Assertions.assertThatThrownBy(() -> gameService.create(gameRequestDTO)).isInstanceOf(GameException.class).hasMessage("Jogo com nome Jogo1 já existe");
    }

    @Test
    public void findByName_WithValidData_ReturnGameResponse() {

        String gameName = "Jogo1";

        Game gameWithId = new Game(1L, gameName, 5, "https");

        Mockito.when(gameRepository.findByName(gameName)).thenReturn(Optional.of(gameWithId));

        GameResponseDTO gameResponse = gameService.findByName(gameName);

        Assertions.assertThat(gameResponse).hasFieldOrProperty("id");
        Assertions.assertThat(gameResponse.getName()).isEqualTo(gameName);
    }

    @Test
    public void findByName_WithNonExistingName_ReturnGameException() {

        String gameName = "Jogo1";

        Mockito.when(gameRepository.findByName(gameName)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> gameService.findByName(gameName)).isInstanceOf(GameException.class).hasMessage("Jogo com nome Jogo1 não existe");
    }
}
