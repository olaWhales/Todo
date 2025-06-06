package com.app.todo.domain.Ports;

import com.app.todo.dto.request.AdminViewNoteRequest;
import com.app.todo.dto.response.AdminViewNoteResponse;

public interface AdminViewNoteService {
    AdminViewNoteResponse adminViewNote(AdminViewNoteRequest adminViewNoteRequest);
}
