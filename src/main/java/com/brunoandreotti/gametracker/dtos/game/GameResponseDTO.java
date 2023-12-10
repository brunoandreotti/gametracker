package com.brunoandreotti.gametracker.dtos.game;

import lombok.Data;

@Data
public class GameResponseDTO {

    Long id;

    String name;

    Integer averageRate;

    String imageUrl;
}
