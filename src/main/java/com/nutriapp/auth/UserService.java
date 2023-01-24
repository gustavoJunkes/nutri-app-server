package com.nutriapp.auth;

import com.nutriapp.auth.User;
import com.nutriapp.domain.Authority;
import com.nutriapp.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(User user);

    UserDto save(UserDto user);

    Optional<User> find(String id);

    Optional<User> findByUsername(String username);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<Authority> getUserAuthorities(User user);

}