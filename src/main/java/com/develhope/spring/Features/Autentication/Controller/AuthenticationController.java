package com.develhope.spring.Features.Autentication.Controller;

import com.develhope.spring.Features.Autentication.DTO.Request.RefreshTokenRequest;
import com.develhope.spring.Features.Autentication.DTO.Request.SignInRequest;
import com.develhope.spring.Features.Autentication.DTO.Request.SignUpRequest;
import com.develhope.spring.Features.Autentication.DTO.Response.JwtAuthenticationResponse;
import com.develhope.spring.Features.Autentication.Service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Allows you to register")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SignUpRequest.class))}),
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        try {
            JwtAuthenticationResponse response = authenticationService.signup(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Allows you to login")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SignInRequest.class))}),
    })
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request) {
        try {
            JwtAuthenticationResponse response = authenticationService.signin(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @Operation(summary = "Allows you to refresh the token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RefreshTokenRequest.class))}),
    })
    @PostMapping("/refreshToken")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }
}
