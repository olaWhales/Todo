package com.app.todo.domain.Ports;

import com.app.todo.api.dto.request.AdminCreateNoteRequest;
import com.app.todo.api.dto.response.AdminCreateNoteResponse;
import org.springframework.stereotype.Component;

@Component
public interface NoteService {
    AdminCreateNoteResponse createPublicNote(AdminCreateNoteRequest adminCreateNoteRequest, String username);
//    Note createPublicNote(Note note);
}
