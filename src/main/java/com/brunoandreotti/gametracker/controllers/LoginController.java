package com.brunoandreotti.gametracker.controllers;

import com.brunoandreotti.gametracker.dtos.user.UserLoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/login")
public class LoginController {


    @PostMapping()
    public ResponseEntity login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return ResponseEntity.ok().build();
    }
}
