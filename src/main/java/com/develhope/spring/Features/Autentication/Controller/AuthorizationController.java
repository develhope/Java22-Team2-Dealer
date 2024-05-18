package com.develhope.spring.Features.Autentication.Controller;

import com.develhope.spring.Features.User.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userDetails")
@RequiredArgsConstructor
public class AuthorizationController {

    @GetMapping
    public ResponseEntity<String> showUserDetails(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok("Here is the user data: " + user);
    }

}
