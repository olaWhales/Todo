package com.app.todo.api;

import com.app.todo.domain.application.AdminEditNoteServiceImpl;
import com.app.todo.dto.request.AdminEditNoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AdminEditNoteController {
    private final AdminEditNoteServiceImpl adminEditNoteService ;

    @PutMapping("/edit_note/{noteId}")
    public ResponseEntity<?>editNote(@PathVariable Long noteId, @RequestBody AdminEditNoteRequest adminEditNoteRequest) {
        try{
            return new ResponseEntity<>(adminEditNoteService.adminEditNote(noteId, adminEditNoteRequest), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
