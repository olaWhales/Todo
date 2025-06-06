package com.app.todo.api;

import com.app.todo.domain.application.AdminEditNoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminEditNoteController {
    private final AdminEditNoteServiceImpl adminEditNoteService ;

}
