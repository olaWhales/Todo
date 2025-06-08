package com.app.todo.api;

import com.app.todo.domain.Ports.UserDeleteNoteService;
import com.app.todo.domain.application.UserDeleteNoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserDeleteNoteController {
    private final UserDeleteNoteService userDeleteNoteService;
    private final UserDeleteNoteServiceImpl userDeleteNoteServiceImpl;

    @DeleteMapping("/delete_note/{noteId}")
    public ResponseEntity<?> delete(@PathVariable Long noteId){
        try{
            return new ResponseEntity<>(userDeleteNoteServiceImpl.adminDeleteNoteResponse(noteId), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
