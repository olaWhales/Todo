package com.app.todo.api;

import com.app.todo.api.dto.request.AdminViewNoteRequest;
import com.app.todo.domain.Ports.AdminViewNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AdminViewNoteController {
    private final AdminViewNoteService adminViewNoteService;
//    @Autowired
//    public AdminViewNoteController(AdminViewNoteService adminViewNoteService) {
//        this.adminViewNoteService = adminViewNoteService;
//    }
    @GetMapping("/view_note")
    public ResponseEntity<?>view(@RequestBody AdminViewNoteRequest adminViewNoteRequest) {
        try{
            return new ResponseEntity<>(adminViewNoteService.adminViewNote(adminViewNoteRequest), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
