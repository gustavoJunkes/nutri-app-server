package com.nutriapp.auth;

import com.nutriapp.auth.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(User user);

    Optional<User> find(String id);

    Optional<User> findByUsername(String username);
}