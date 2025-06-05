package com.app.todo.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProtectedController {

    @GetMapping("/protected")
    public String protectedPage(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaim("preferred_username");
        System.out.println("Protected endpoint hit by: " + username);
        return String.format("{\"message\": \"Welcome to the protected API, %s!\"}", username);
    }
    }