package com.app.todo.domain.application;

import com.app.todo.Utilities.ApplicationUtility;
import com.app.todo.domain.Ports.UserEditNoteService;
import com.app.todo.domain.model.NoteEntity;
import com.app.todo.dto.request.UserEditNoteRequest;
import com.app.todo.dto.response.UserEditNoteResponse;
import com.app.todo.infrastructure.NoteJpaRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.app.todo.Utilities.ApplicationUtility.*;

@Service
@AllArgsConstructor
public class UserEditNoteServiceImpl implements UserEditNoteService {
    private final NoteJpaRepo noteJpaRepo;

    @Override
    public UserEditNoteResponse adminEditNote(Long noteId, UserEditNoteRequest userEditNoteRequest) {
        String userId = ApplicationUtility.userAuthentication();
        NoteEntity noteEntity = noteJpaRepo.findById(noteId).orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND));
        if (!noteEntity.getCreatedBy().equals(userId)) {throw new IllegalArgumentException(NOT_PERMITTED_MESSAGE);}
        noteEntity.setContent(userEditNoteRequest.getContent());
        noteEntity.setTitle(userEditNoteRequest.getTitle());
        noteEntity.setCreatedDate(LocalDateTime.now());
        noteJpaRepo.save(noteEntity);

        return new UserEditNoteResponse(
                noteEntity.getTitle(),
                noteEntity.getContent(),
                noteEntity.getCreatedDate()
        );
    }
}
