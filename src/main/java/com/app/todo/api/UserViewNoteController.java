package com.app.todo.api;

import com.app.todo.domain.Ports.UserViewNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserViewNoteController {
    private final UserViewNoteService userViewNoteService;

    @GetMapping("/view_note/{noteId}")
    public ResponseEntity<?> view(@PathVariable Long noteId) {
        try {
            return new ResponseEntity<>(userViewNoteService.adminViewNote(noteId, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}