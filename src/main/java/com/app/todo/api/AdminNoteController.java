package com.app.todo.api;

import com.app.todo.domain.Ports.NoteService;
import com.app.todo.domain.model.Note;
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
            @RequestBody Note note,
            @AuthenticationPrincipal Jwt jwt) {
        try{
            return new ResponseEntity<>(noteService.createPublicNote(note), HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_GATEWAY);
        }
//        String username = jwt.getClaimAsString("preferred_username");
//        return noteService.createPublicNote(
//                new Note(null, note.title(), note.content(), true, username)
//        );
    }
}
