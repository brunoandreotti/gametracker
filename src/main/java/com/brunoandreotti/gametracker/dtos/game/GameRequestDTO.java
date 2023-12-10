package com.brunoandreotti.gametracker.dtos.game;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    String name;

    @NotBlank(message = "Nome é obrigatório")
    String imageUrl;
}
