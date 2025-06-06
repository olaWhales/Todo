package com.app.todo.domain.Ports;

import com.app.todo.api.dto.request.AdminDeleteNoteRequest;
import com.app.todo.api.dto.response.AdminDeleteNoteResponse;

public interface AdminDeleteNoteService {
    AdminDeleteNoteResponse adminDeleteNoteResponse(AdminDeleteNoteRequest request);
}
