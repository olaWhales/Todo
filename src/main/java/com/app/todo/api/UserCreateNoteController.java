package com.app.todo.api;

import com.app.todo.dto.request.UserCreateNoteRequest;
import com.app.todo.domain.Ports.UserCreateNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserCreateNoteController {
    private final UserCreateNoteService userCreateNoteService;

    @PostMapping("/create_note/")
    public ResponseEntity<?> createPublicNote(
            @RequestBody UserCreateNoteRequest userCreateNoteRequest,
            @AuthenticationPrincipal Jwt jwt) {
        try{
            String username = jwt.getClaimAsString("sub");
            return new ResponseEntity<>(userCreateNoteService.createPublicNote(userCreateNoteRequest, username), HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }
}
