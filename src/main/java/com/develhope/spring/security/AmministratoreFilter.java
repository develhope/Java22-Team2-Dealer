package com.develhope.spring.security;

import com.auth0.jwt.interfaces.Header;
import com.develhope.spring.DTOs.auth.JwtDto;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class AmministratoreFilter implements Filter {

    private final JWTUtils jwtUtils;

    public AmministratoreFilter(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String header = httpRequest.getHeader("Authorization");
        if (!StringUtils.hasText(header)) {
            throw new ServletException("Authorization header is missing");
        }

        String jwt = header.substring(7);
        if (!StringUtils.hasText(jwt)) {
            throw new ServletException("Token is invalid");
        }

        JwtDto jwtDto = jwtUtils.verifyToken(jwt);
        if (!jwtDto.getTipoUtente().equals("AMMINISTRATORE")) {
            throw new ServletException("Invalid user type");
        }


        chain.doFilter(request, response);
    }

}
