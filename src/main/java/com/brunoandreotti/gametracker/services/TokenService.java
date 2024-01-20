package com.brunoandreotti.gametracker.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.brunoandreotti.gametracker.domain.models.user.User;
import com.brunoandreotti.gametracker.exceptions.JWTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String TOKEN_SECRET;

    public String generateToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            return JWT.create()
                    .withIssuer("minha-api") //Criador do token
                    .withSubject(user.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTException("Erro ao gerar token de autenticação", exception);
        }
    }

    public String validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            return JWT.require(algorithm)
                    .withIssuer("minha-api")
                    .build().verify(token)
                    .getSubject();

        } catch (JWTCreationException exception) {
            throw new JWTException("Erro ao validar token", exception);
        }
    }

    private Instant generateExpirationDate() {
        //Gera um hora a partir da hora atual mais duas horas para o tempo de expiração do token
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
