package com.brunoandreotti.gametracker.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDTO {

    Integer statusCode;

    String errorMessage;
}
