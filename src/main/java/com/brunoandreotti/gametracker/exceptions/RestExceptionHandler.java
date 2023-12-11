package com.brunoandreotti.gametracker.exceptions;

import com.brunoandreotti.gametracker.dtos.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { GameException.class, GameTrackException.class } )
    private ResponseEntity<ApiResponseDTO<ErrorDTO>> handleGameException(RuntimeException ex) {

        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return new ResponseEntity<>(new ApiResponseDTO<>(false, Integer.toString(HttpStatus.BAD_REQUEST.value()), errorResponse), HttpStatus.BAD_REQUEST);
    }

}
