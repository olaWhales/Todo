package com.app.todo.domain.Ports;

import com.app.todo.dto.request.UserViewNoteRequest;
import com.app.todo.dto.response.UserViewNoteResponse;

public interface UserViewNoteService {
    UserViewNoteResponse adminViewNote(Long noteId, UserViewNoteRequest userViewNoteRequest);
}
