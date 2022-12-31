package com.nutriapp.auth.config;

import com.nutriapp.auth.TokenService;
import com.nutriapp.auth.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;

    private TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        final String auhHeader = request.getHeader("AUTHORIZAION");
        final String userEmail;
        final String jwtToken;

        if (auhHeader == null || !auhHeader.startsWith("Baerer")) {
            filterChain.doFilter(request, response);
            return;
        }
        // position of token in char array that contais the baerer token
        jwtToken = auhHeader.substring(7);

        userEmail = tokenService.extractUsernameFromToken(jwtToken);

        if (userEmail != null && SecurityContextHolder.getContext() == null) {
            User user = (User) userDetailsService.loadUserByUsername(userEmail);
            boolean isTokenValid = tokenService.isTokenValid(jwtToken, user);

            if (isTokenValid) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null);
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request, response);
    }
}
