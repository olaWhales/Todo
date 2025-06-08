package com.app.todo.domain.Ports;

import com.app.todo.dto.response.UserDeleteNoteResponse;

public interface UserDeleteNoteService {
    UserDeleteNoteResponse adminDeleteNoteResponse(Long noteId);
}
