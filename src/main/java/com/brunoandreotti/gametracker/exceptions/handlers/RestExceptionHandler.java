package com.brunoandreotti.gametracker.exceptions.handlers;

import com.brunoandreotti.gametracker.dtos.ApiResponseDTO;
import com.brunoandreotti.gametracker.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { GameException.class, GameTrackException.class } )
    private ResponseEntity<ApiResponseDTO<ErrorDTO>> handleGameException(RuntimeException ex) {

        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return new ResponseEntity<>(new ApiResponseDTO<>(false, Integer.toString(HttpStatus.BAD_REQUEST.value()), errorResponse), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserException.class )
    private ResponseEntity<ApiResponseDTO<ErrorDTO>> handleUserException(RuntimeException ex) {

        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return new ResponseEntity<>(new ApiResponseDTO<>(false, Integer.toString(HttpStatus.BAD_REQUEST.value()), errorResponse), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = JWTException.class )
    private ResponseEntity<ApiResponseDTO<ErrorDTO>> JWTExceptionHandler(RuntimeException ex) {

        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return new ResponseEntity<>(new ApiResponseDTO<>(false, Integer.toString(HttpStatus.BAD_REQUEST.value()), errorResponse), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AuthenticationException.class )
    private ResponseEntity<ApiResponseDTO<ErrorDTO>> AuthorizationHeaderExceptionHandler(RuntimeException ex) {

        ErrorDTO errorResponse = new ErrorDTO(ex.getMessage());

        return new ResponseEntity<>(new ApiResponseDTO<>(false, Integer.toString(HttpStatus.BAD_REQUEST.value()), errorResponse), HttpStatus.BAD_REQUEST);
    }



}
