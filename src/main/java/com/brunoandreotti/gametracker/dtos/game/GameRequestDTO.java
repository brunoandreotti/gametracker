package com.brunoandreotti.gametracker.dtos.game;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameRequestDTO {

    String name;

    String imageUrl;
}
