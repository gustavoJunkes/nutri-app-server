package com.nutriapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomCorsFilter extends OncePerRequestFilter {

    @Value("${custom.cors.allowOrigin:*}")
    private String allowOrigin;

    @Value("${custom.cors.allowMethods:GET, POST, PUT, DELETE, OPTIONS}")
    private String allowMethods;

    @Value("${custom.cors.allowHeaders:Content-Type, Authorization}")
    private String allowHeaders;

    @Value("${custom.cors.allowCredentials:true}")
    private String allowCredentials;

    @Value("${custom.cors.maxAge:3600}")
    private String maxAge;

//    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", allowOrigin);

        if (HttpMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Methods", allowMethods);
//            response.addHeader("Access-Control-Allow-Headers", allowHeaders);
            response.addHeader("Access-Control-Allow-Credentials", allowCredentials);
            response.addHeader("Access-Control-Max-Age", maxAge);
            response.setStatus(HttpServletResponse.SC_OK);// ,acces-control-allow-headers,acces-control-allow-methods,access-control-allow-origin,content-type
            response.addHeader("Access-Control-Allow-Headers", "acces-control-allow-credentials");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", allowOrigin);

        if (HttpMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Methods", allowMethods);
            response.addHeader("Access-Control-Allow-Headers", allowHeaders);
            response.addHeader("Access-Control-Allow-Credentials", allowCredentials);
            response.addHeader("Access-Control-Max-Age", maxAge);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}