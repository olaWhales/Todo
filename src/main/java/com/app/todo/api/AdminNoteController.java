package com.app.todo.api;

import com.app.todo.api.dto.request.AdminCreateNoteRequest;
import com.app.todo.domain.Ports.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/notes")
@RequiredArgsConstructor
public class AdminNoteController {
    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<?> createPublicNote(
            @RequestBody AdminCreateNoteRequest adminCreateNoteRequest,
            @AuthenticationPrincipal Jwt jwt) {
        try{
            String username = jwt.getClaimAsString("preferred_username");
            return new ResponseEntity<>(noteService.createPublicNote(adminCreateNoteRequest, username), HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }
}
