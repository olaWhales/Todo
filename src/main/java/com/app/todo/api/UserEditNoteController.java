package com.app.todo.api;

import com.app.todo.domain.application.UserEditNoteServiceImpl;
import com.app.todo.dto.request.UserEditNoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserEditNoteController {
    private final UserEditNoteServiceImpl adminEditNoteService ;

    @PutMapping("/edit_note/{noteId}")
    public ResponseEntity<?>editNote(@PathVariable Long noteId, @RequestBody UserEditNoteRequest userEditNoteRequest) {
        try{
            return new ResponseEntity<>(adminEditNoteService.adminEditNote(noteId, userEditNoteRequest), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
