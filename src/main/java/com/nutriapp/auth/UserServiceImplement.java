package com.nutriapp.auth;

import com.nutriapp.domain.Authority;
import com.nutriapp.dto.UserDto;
import com.nutriapp.repository.UserRepository;
import com.nutriapp.service.authority.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
@Primary
final class UserServiceImpl implements UserService {


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AuthorityService authorityService;

    @Bean
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        org.springframework.security.core.userdetails.User.UserBuilder users = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Override
    public User save(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public UserDto save(UserDto userDto) {

        if (Objects.isNull(userDto.getAuthorities()) || userDto.getAuthorities().isEmpty()) {
            Authority authority = new Authority();
            authority.setRole("ROLE_USER");
            authorityService.save(authority);
            userDto.setAuthorities(List.of(authority));
        }

        if (Objects.nonNull(userDto.getAuthorities())) {
            userDto.getAuthorities()
                    .forEach(authority -> {
                        if (!authorityService.exists(authority))
                            throw new IllegalArgumentException("The given authority does not exist!");
                    });
        }

        User savedUser = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .authorities(userDto.getAuthorities())
                .build()
                .addRole("ADMIN", "USER");

        savedUser = this.save(savedUser);



        return UserDto.builder()
                .username(savedUser.getUsername())
                .password(savedUser.getPassword())
//                .authorities(savedUser.getAuthorities())
                .build();
    }

    @Override
    public Optional<User> find(final String id) {
        Optional<User> user = userRepository.findById(id);

        user.ifPresent(user1 -> {
            user1.setAuthorities(getUserAuthorities(user.get()));
        });

        return user;
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return userRepository.findFirstByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public List<Authority> getUserAuthorities(User user) {
        return authorityService.findAuthoritiesByUser(user);
    }


}