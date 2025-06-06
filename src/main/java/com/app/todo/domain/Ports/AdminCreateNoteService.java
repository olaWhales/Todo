package com.app.todo.domain.Ports;

import com.app.todo.dto.request.AdminCreateNoteRequest;
import com.app.todo.dto.response.AdminCreateNoteResponse;
import org.springframework.stereotype.Component;

@Component
public interface AdminCreateNoteService {
    AdminCreateNoteResponse createPublicNote(AdminCreateNoteRequest adminCreateNoteRequest, String userId);
//    Note createPublicNote(Note note);
}
