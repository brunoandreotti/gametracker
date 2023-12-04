package com.brunoandreotti.gametracker.utils;

import com.brunoandreotti.gametracker.domain.models.Game;
import com.brunoandreotti.gametracker.dtos.game.GameRequestDTO;
import com.brunoandreotti.gametracker.dtos.game.GameResponseDTO;

public class GameMapper {

    public static Game fromGameRequestToGame(GameRequestDTO gameData) {
        Game game = new Game();

        game.setName(gameData.getName());
        game.setAverageRate(5);
        game.setImageUrl(gameData.getImageUrl());

        return game;
    }

    public static GameResponseDTO fromGameToGameResponse(Game gameData) {
        GameResponseDTO gameResponse = new GameResponseDTO();

        gameResponse.setId(gameData.getId());
        gameResponse.setName(gameData.getName());
        gameResponse.setAverageRate(gameData.getAverageRate());
        gameResponse.setImageUrl(gameData.getImageUrl());

        return gameResponse;
    }


}
