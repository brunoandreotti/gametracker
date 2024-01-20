package com.brunoandreotti.gametracker.controllers;

import com.brunoandreotti.gametracker.domain.models.user.User;
import com.brunoandreotti.gametracker.dtos.ApiResponseDTO;
import com.brunoandreotti.gametracker.dtos.TokenResponseDTO;
import com.brunoandreotti.gametracker.dtos.user.UserLoginDTO;
import com.brunoandreotti.gametracker.dtos.user.UserRegisterDTO;
import com.brunoandreotti.gametracker.dtos.user.UserRegisterResponseDTO;
import com.brunoandreotti.gametracker.services.TokenService;
import com.brunoandreotti.gametracker.services.UserService;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<TokenResponseDTO>> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {

        UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(userLoginDTO.getLogin(), userLoginDTO.getPassword());
        Authentication authObject = this.authenticationManager.authenticate(usernamePasswordToken);

       String token = tokenService.generateToken((User) authObject.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>(true, Integer.toString(HttpStatus.OK.value()), new TokenResponseDTO(token)));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<UserRegisterResponseDTO>> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {

        UserRegisterResponseDTO user = userService.createUser(userRegisterDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<>(true, Integer.toString(HttpStatus.CREATED.value()), user));
    }
}
