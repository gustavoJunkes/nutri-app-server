package com.nutriapp.auth;

import java.util.Map;

public interface TokenService {

    @Deprecated
    String newToken(final Map<String, String> attributes);
    Map<String, String> verify(String token);

    boolean isTokenValid(String token, User user);

    String createToken(Map<String, Object> claims, User user);

    String extractUsernameFromToken(String token);

    String generateToken(User user);
}