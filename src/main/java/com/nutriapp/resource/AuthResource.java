package com.nutriapp.resource;

import com.nutriapp.auth.TokenService;
import com.nutriapp.auth.User;
import com.nutriapp.auth.UserService;
import com.nutriapp.dto.auth.AuthenticateDto;
import com.nutriapp.dto.auth.GeneratedTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AuthResource {

    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<GeneratedTokenDto> authenticate(@RequestBody AuthenticateDto request) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = (User) userService.loadUserByUsername(request.getUsername());

        if (user != null) {
            return ResponseEntity.of(Optional.of(new GeneratedTokenDto(tokenService.generateToken(user))));
        }
        return ResponseEntity.status(401).body(null);
    }
}
