package com.brunoandreotti.gametracker.dtos.gametrack;

import com.brunoandreotti.gametracker.enums.GameStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class GameTrackRequestDTO {

    @NotNull()
    GameStatus status;

    @Min(value = 1, message = "Rate é obrigatório e precisa ser um valor entre 1 e 5")
    @Max(value = 5, message = "Rate é obrigatório e precisa ser um valor entre 1 e 5")
    Integer rate;

    @DecimalMin(value = "0.0", message = "Horas jogadas deve ser um decimal")
    Double hoursPlayed;

    @Min(value = 0, message = "gameId é obrigatório")
    @NotNull
    Long gameId;
}
