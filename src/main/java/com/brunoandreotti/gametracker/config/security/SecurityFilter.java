package com.brunoandreotti.gametracker.config.security;

import com.brunoandreotti.gametracker.domain.repositories.UserRepository;
import com.brunoandreotti.gametracker.exceptions.AuthorizationHeaderException;

import com.brunoandreotti.gametracker.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token != null) {
            String login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByLogin(login);

            UsernamePasswordAuthenticationToken authenticationDetails = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationDetails);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest req) {
        String authHeader = req.getHeader("Authorization");

        if (authHeader == null) return null;
        //Irá retornar token sem o "Bearer"
        return authHeader.replace("Bearer ", "");
    }


}
