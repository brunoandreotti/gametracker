package com.brunoandreotti.gametracker.services;

import com.brunoandreotti.gametracker.domain.models.Game;
import com.brunoandreotti.gametracker.domain.repositories.GameRepository;
import com.brunoandreotti.gametracker.dtos.game.GameRequestDTO;
import com.brunoandreotti.gametracker.dtos.game.GameResponseDTO;
import com.brunoandreotti.gametracker.exceptions.GameException;
import com.brunoandreotti.gametracker.utils.ErrorStrings;
import com.brunoandreotti.gametracker.utils.GameMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameResponseDTO create(GameRequestDTO gameData) {

        Game game = GameMapper.fromGameRequestToGame(gameData);

        Boolean gameExistsByName = gameRepository.existsByNameIgnoreCase(game.getName());

        if (Boolean.TRUE.equals(gameExistsByName)) {
            throw new GameException(String.format(ErrorStrings.EXISTING_ENTITY, "Jogo", "nome", game.getName()));
        }

        return GameMapper.fromGameToGameResponse(gameRepository.save(game));
    }

    public GameResponseDTO findByName(String name) {

        return GameMapper.fromGameToGameResponse(verifyIfGameExistsByName(name));
    }

    public List<String> findGamesName(String name) {

        return gameRepository.findByNameStartingWithIgnoreCase(name).stream().map(Game::getName).toList();
    }

    public List<String> findAllGamesName() {

        return gameRepository.findAll().stream().map(Game::getName).toList();
    }

    public void deleteByName(String name) {

        gameRepository.delete(verifyIfGameExistsByName(name));
    }

    private Game verifyIfGameExistsByName(String name) {
        Optional<Game> game = gameRepository.findByName(name);

        if (game.isEmpty()) {
            throw new GameException(String.format(ErrorStrings.NOT_EXISTING_ENTITY, "Jogo", "nome", name));
        }

        return game.get();
    }
}
