package com.brunoandreotti.gametracker.services;

import com.brunoandreotti.gametracker.domain.models.user.User;
import com.brunoandreotti.gametracker.domain.models.user.UserRole;
import com.brunoandreotti.gametracker.domain.repositories.UserRepository;
import com.brunoandreotti.gametracker.dtos.user.UserRegisterDTO;
import com.brunoandreotti.gametracker.dtos.user.UserRegisterResponseDTO;
import com.brunoandreotti.gametracker.exceptions.UserException;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRegisterResponseDTO createUser(UserRegisterDTO userData) {

        if (userRepository.findByLogin(userData.getLogin()) != null) {
            throw new UserException("Usuário com este login já cadastrado!");
        }


        String hashedPassword = new BCryptPasswordEncoder().encode(userData.getPassword());

        User newUser = new User();

        BeanUtils.copyProperties(userData, newUser, "password");

        newUser.setPassword(hashedPassword);

        return new UserRegisterResponseDTO(userRepository.save(newUser));
    }
}
