package com.app.todo.api;

import com.app.todo.dto.request.AdminCreateNoteRequest;
import com.app.todo.domain.Ports.AdminCreateNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/notes")
@RequiredArgsConstructor
public class AdminCreateNoteController {
    private final AdminCreateNoteService adminCreateNoteService;

    @PostMapping
    public ResponseEntity<?> createPublicNote(
            @RequestBody AdminCreateNoteRequest adminCreateNoteRequest,
            @AuthenticationPrincipal Jwt jwt) {
        try{
            String username = jwt.getClaimAsString("sub");
            return new ResponseEntity<>(adminCreateNoteService.createPublicNote(adminCreateNoteRequest, username), HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }
}
