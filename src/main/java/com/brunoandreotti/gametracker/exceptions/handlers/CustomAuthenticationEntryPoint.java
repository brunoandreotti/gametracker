package com.brunoandreotti.gametracker.exceptions.handlers;

import com.brunoandreotti.gametracker.dtos.ApiResponseDTO;
import com.brunoandreotti.gametracker.exceptions.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(401);

        ErrorDTO errorResponse = new ErrorDTO("Acesso Negado!");

        ObjectMapper mapper = new ObjectMapper();
        res.getWriter().write(mapper.writeValueAsString(new ApiResponseDTO<>(false, Integer.toString(HttpStatus.UNAUTHORIZED.value()), errorResponse)
        ));
    }
}