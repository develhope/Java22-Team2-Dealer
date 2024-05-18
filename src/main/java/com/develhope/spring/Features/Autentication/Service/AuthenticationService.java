package com.develhope.spring.Features.Autentication.Service;

import com.develhope.spring.Features.Autentication.DTO.Request.RefreshTokenRequest;
import com.develhope.spring.Features.Autentication.DTO.Request.SignInRequest;
import com.develhope.spring.Features.Autentication.DTO.Request.SignUpRequest;
import com.develhope.spring.Features.Autentication.DTO.Response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest request);

}
