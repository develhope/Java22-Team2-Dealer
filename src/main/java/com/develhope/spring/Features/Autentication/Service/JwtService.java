package com.develhope.spring.Features.Autentication.Service;

import com.develhope.spring.Features.Autentication.Entity.RefreshToken;
import com.develhope.spring.Features.User.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    RefreshToken generateRefreshToken(User user);

    boolean isRefreshTokenExpired(RefreshToken token);

    boolean isTokenValid(String token, UserDetails userDetails);

}
