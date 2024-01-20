package com.brunoandreotti.gametracker.dtos.user;

import com.brunoandreotti.gametracker.domain.models.user.User;
import com.brunoandreotti.gametracker.domain.models.user.UserRole;
import lombok.Data;

@Data
public class UserRegisterResponseDTO {

    private String login;

    private UserRole role;

    public UserRegisterResponseDTO(User user) {
        this.login = user.getLogin();
        this.role = user.getRole();
    }
}
