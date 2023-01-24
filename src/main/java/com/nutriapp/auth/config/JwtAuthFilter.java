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


/**
 * CORRETO
 * */
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


    /**
     * Estou sendo trancado pelo AuthorizationFilter, suspeito que tenha alguma conf ou minha arquitetura esta incompleta, pois ele verifica se já estou autenticado
     *
     * */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("AUTHORIZATION");
        final String userEmail;
        final String jwtToken;

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        // position of token in char array that contais the baerer token
        jwtToken = authHeader.substring(7);

        userEmail = tokenService.extractUsernameFromToken(jwtToken);

        var context = SecurityContextHolder.getContext();
        // TODO: 19/01/2023 Verify context properly, it is always going to return an object with null references but never actually null.
        if (userEmail != null /*&& SecurityContextHolder.getContext() == null*/) {
            User user = (User) userDetailsService.loadUserByUsername(userEmail);
            boolean isTokenValid = tokenService.isTokenValid(jwtToken, user);

            if (isTokenValid) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request, response);
    }
}
