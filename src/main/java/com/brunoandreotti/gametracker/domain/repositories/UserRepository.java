package com.brunoandreotti.gametracker.domain.repositories;

import com.brunoandreotti.gametracker.domain.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);
}
