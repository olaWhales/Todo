package com.app.todo.domain.Ports;

import com.app.todo.dto.response.UserViewNoteResponse;

import java.util.List;

public interface UserViewAllNotesService {
    List<UserViewNoteResponse> viewAllNotes(String userId);
}