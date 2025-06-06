package com.app.todo.domain.Ports;

import com.app.todo.dto.request.AdminDeleteNoteRequest;
import com.app.todo.dto.response.AdminDeleteNoteResponse;

public interface AdminDeleteNoteService {
    AdminDeleteNoteResponse adminDeleteNoteResponse(AdminDeleteNoteRequest request);
}
