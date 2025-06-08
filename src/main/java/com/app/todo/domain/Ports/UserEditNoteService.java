package com.app.todo.domain.Ports;

import com.app.todo.dto.request.UserEditNoteRequest;
import com.app.todo.dto.response.UserEditNoteResponse;

public interface UserEditNoteService {
    UserEditNoteResponse adminEditNote(Long noteId, UserEditNoteRequest userEditNoteRequest);
}
