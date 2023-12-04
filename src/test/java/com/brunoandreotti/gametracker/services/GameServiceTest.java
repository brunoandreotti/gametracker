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

import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void findName_WithValidData_ReturnStringList() {

        String gameName1 = "Jogo1";
        String gameName2 = "Jogo2";

        Game gameWithId1 = new Game(1L, gameName1, 5, "https");
        Game gameWithId2 = new Game(2L, gameName2, 5, "https");

        Mockito.when(gameRepository.findByNameStartingWithIgnoreCase(Mockito.anyString())).thenReturn(List.of(gameWithId1, gameWithId2));

        List<String> result = gameService.findGamesName("j");

        Assertions.assertThat(result).hasSize(2).contains(gameName1).contains(gameName2);


    }

    @Test
    public void findName_WithNonExisting_ReturnEmptyList() {

        Mockito.when(gameRepository.findByNameStartingWithIgnoreCase(Mockito.anyString())).thenReturn(new ArrayList<>());

        List<String> result = gameService.findGamesName("j");

        Assertions.assertThat(result).hasSize(0);


    }

    @Test
    public void findAllName_WithValidData_ReturnStringList() {

        String gameName1 = "Jogo1";
        String gameName2 = "Jogo2";
        String gameName3 = "Game3";

        Game gameWithId1 = new Game(1L, gameName1, 5, "https");
        Game gameWithId2 = new Game(2L, gameName2, 5, "https");
        Game gameWithId3 = new Game(2L, gameName3, 5, "https");

        Mockito.when(gameRepository.findAll()).thenReturn(List.of(gameWithId1, gameWithId2, gameWithId3));

        List<String> result = gameService.findAllGamesName();

        Assertions.assertThat(result).hasSize(3).contains(gameName1).contains(gameName2).contains(gameName3);


    }

    @Test
    public void deleteNyName_WithValidData_ReturnVoid() {

        String gameName = "Jogo1";


        Game gameWithId = new Game(1L, gameName, 5, "https");


        Mockito.when(gameRepository.findByName(gameName)).thenReturn(Optional.of(gameWithId));

        gameService.deleteByName(gameName);

        Mockito.verify(gameRepository, Mockito.times(1)).delete(gameWithId);


    }

    @Test
    public void deleteNyName_WithNonExistingData_ReturnGameException() {

        String gameName = "Jogo1";

        Mockito.when(gameRepository.findByName(gameName)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> gameService.deleteByName(gameName)).isInstanceOf(GameException.class).hasMessage("Jogo com nome Jogo1 não existe");


    }
}
