package com.brunoandreotti.gametracker.services;


import com.brunoandreotti.gametracker.domain.models.Game;
import com.brunoandreotti.gametracker.domain.models.GameTrack;

import com.brunoandreotti.gametracker.domain.repositories.GameTrackRepository;
import com.brunoandreotti.gametracker.dtos.gametrack.GameTrackRequestDTO;
import com.brunoandreotti.gametracker.dtos.gametrack.GameTrackResponseDTO;
import com.brunoandreotti.gametracker.exceptions.GameTrackException;
import com.brunoandreotti.gametracker.utils.ErrorStrings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameTrackService {

    private final GameTrackRepository gameTrackRepository;
    private final GameService gameService;

    public GameTrackService(GameTrackRepository gameTrackRepository, GameService gameService) {
        this.gameTrackRepository = gameTrackRepository;
        this.gameService = gameService;
    }

    public GameTrackResponseDTO create(GameTrackRequestDTO gameTrackData) {

        GameTrack gameTrack = new GameTrack(gameTrackData);

        Game game = gameService.verifyIfGameExistsById(gameTrackData.getGameId());

        gameTrack.setGame(game);

        return new GameTrackResponseDTO(gameTrackRepository.save(gameTrack));
    }

    public List<GameTrackResponseDTO> findAll() {

        return gameTrackRepository.findAll().stream().map(GameTrackResponseDTO::new).toList();
    }

    public List<GameTrackResponseDTO> findByGameName(String name) {

        Game game = gameService.verifyIfGameExistsByName(name);

        return gameTrackRepository.findGameTrackByGame(game).stream().map(GameTrackResponseDTO::new).toList();
    }

    public void deleteById(Long id) {

        gameTrackRepository.delete(verifyIfGameTrackExistsById(id));
    }

    public GameTrackResponseDTO updateById(Long id, GameTrackRequestDTO gameTrackData) {

        GameTrack gameTrack = verifyIfGameTrackExistsById(id);

        Game game = gameService.verifyIfGameExistsById(gameTrackData.getGameId());

        BeanUtils.copyProperties(gameTrackData, gameTrack, "id");

        gameTrack.setGame(game);

        return new GameTrackResponseDTO(gameTrackRepository.save(gameTrack));

    }

    private GameTrack verifyIfGameTrackExistsById(Long id) {
        Optional<GameTrack> gameTrack = gameTrackRepository.findById(id);

        if (gameTrack.isEmpty()) {
            throw  new GameTrackException(String.format(ErrorStrings.NOT_EXISTING_ENTITY, "GameTrack", "id", id));
        }

        return gameTrack.get();
    }
}
