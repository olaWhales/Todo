package com.app.todo.api;

import com.app.todo.dto.request.AdminDeleteNoteRequest;
import com.app.todo.domain.Ports.AdminDeleteNoteService;
import com.app.todo.domain.application.AdminDeleteNoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AdminDeleteNoteController {
    private final AdminDeleteNoteService adminDeleteNoteService;
    private final AdminDeleteNoteServiceImpl adminDeleteNoteServiceImpl;

    @DeleteMapping("/delete_note")
    public ResponseEntity<?> delete(@RequestBody AdminDeleteNoteRequest adminDeleteNoteRequest){
        try{
            return new ResponseEntity<>(adminDeleteNoteServiceImpl.adminDeleteNoteResponse(adminDeleteNoteRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
