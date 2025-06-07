package com.app.todo.domain.Ports;

import com.app.todo.dto.request.AdminEditNoteRequest;
import com.app.todo.dto.response.AdminEditNoteResponse;

public interface AdminEditNoteService {
    AdminEditNoteResponse adminEditNote(Long noteId, AdminEditNoteRequest adminEditNoteRequest);
}
