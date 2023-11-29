package com.brunoandreotti.gametracker.services;

import com.brunoandreotti.gametracker.domain.models.Game;
import com.brunoandreotti.gametracker.domain.repositories.GameRepository;
import com.brunoandreotti.gametracker.dtos.game.GameRequestDTO;
import com.brunoandreotti.gametracker.dtos.game.GameResponseDTO;
import com.brunoandreotti.gametracker.exceptions.GameException;
import com.brunoandreotti.gametracker.utils.ErrorStrings;
import com.brunoandreotti.gametracker.utils.GameMapper;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameResponseDTO create(GameRequestDTO gameData) {

        Game game = GameMapper.fromGameRequestToGame(gameData);

        Boolean gameExistsByName = gameRepository.existsByName(game.getName());

        if (Boolean.TRUE.equals(gameExistsByName)) {
            throw new GameException(String.format(ErrorStrings.EXISTING_ENTITY, "Jogo", "nome", game.getName()));
        }

        return GameMapper.fromGameToGameResponse(gameRepository.save(game));
    }
}
