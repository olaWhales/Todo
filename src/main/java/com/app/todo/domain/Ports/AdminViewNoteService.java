package com.app.todo.domain.Ports;

import com.app.todo.api.dto.request.AdminViewNoteRequest;
import com.app.todo.api.dto.response.AdminViewNoteResponse;

public interface AdminViewNoteService {
    AdminViewNoteResponse adminViewNote(AdminViewNoteRequest adminViewNoteRequest);
}
