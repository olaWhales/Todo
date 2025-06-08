package com.app.todo.domain.Ports;

import com.app.todo.dto.request.UserCreateNoteRequest;
import com.app.todo.dto.response.UserCreateNoteResponse;
import org.springframework.stereotype.Component;

@Component
public interface UserCreateNoteService {
    UserCreateNoteResponse createPublicNote(UserCreateNoteRequest userCreateNoteRequest, String userId);
//    Note createPublicNote(Note note);
}
