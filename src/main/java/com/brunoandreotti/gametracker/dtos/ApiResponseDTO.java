package com.brunoandreotti.gametracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDTO<T> {
    private boolean success;
    private String statusCode  ;
    private T data;

}
