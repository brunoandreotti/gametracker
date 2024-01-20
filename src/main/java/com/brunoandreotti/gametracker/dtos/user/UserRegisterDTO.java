package com.brunoandreotti.gametracker.dtos.user;

import com.brunoandreotti.gametracker.domain.models.user.UserRole;
import lombok.Data;

@Data
public class UserRegisterDTO {

    private String login;

    private String password;

    private UserRole role;
}
