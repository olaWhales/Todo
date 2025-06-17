package com.app.todo.api;

import com.app.todo.domain.Ports.UserViewAllNotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserViewAllNotesController {
    private final UserViewAllNotesService userViewAllNotesService;

    @GetMapping("/notes")
    @PreAuthorize("hasAuthority('SCOPE_profile')")
    public ResponseEntity<?> viewAll(@AuthenticationPrincipal Jwt jwt) {
        try {
            return new ResponseEntity<>(userViewAllNotesService.viewAllNotes(jwt.getSubject()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}