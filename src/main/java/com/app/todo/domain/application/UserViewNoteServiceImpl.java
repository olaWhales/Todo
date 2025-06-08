package com.app.todo.domain.application;

import com.app.todo.Utilities.ApplicationUtility;
import com.app.todo.dto.request.UserViewNoteRequest;
import com.app.todo.dto.response.UserViewNoteResponse;
import com.app.todo.domain.Ports.UserViewNoteService;
import com.app.todo.domain.model.NoteEntity;
import com.app.todo.infrastructure.NoteJpaRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import static com.app.todo.Utilities.ApplicationUtility.*;

@Service
@AllArgsConstructor
public class UserViewNoteServiceImpl implements UserViewNoteService {
    private NoteJpaRepo noteJpaRepo;

    @Override
    public UserViewNoteResponse adminViewNote(Long noteId, UserViewNoteRequest userViewNoteRequest) {
        String userId = ApplicationUtility.userAuthentication();
        NoteEntity noteEntity = noteJpaRepo.findById(noteId).orElseThrow(()-> new IllegalArgumentException(USER_NOT_FOUND));
        if(!noteEntity.getCreatedBy().equals(userId)) {throw new IllegalStateException(NOT_VIEW_PERMITTED_MESSAGE);}
        UserViewNoteResponse userViewNoteResponse = new UserViewNoteResponse();
        userViewNoteResponse.setTitle(noteEntity.getTitle());
        userViewNoteResponse.setContent(noteEntity.getContent());
        userViewNoteResponse.setPublic(noteEntity.isPublic());
        userViewNoteResponse.setCreatedBy(noteEntity.getCreatedBy());
        return userViewNoteResponse;
    }
}
