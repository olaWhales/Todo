package com.app.todo.domain.application;

import com.app.todo.domain.Ports.AdminEditNoteService;
import com.app.todo.domain.model.NoteEntity;
import com.app.todo.dto.request.AdminEditNoteRequest;
import com.app.todo.dto.response.AdminEditNoteResponse;
import com.app.todo.infrastructure.NoteJpaRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AdminEditNoteServiceImpl implements AdminEditNoteService {
    private final NoteJpaRepo noteJpaRepo;

    @Override
    public AdminEditNoteResponse adminEditNote(Long noteId, AdminEditNoteRequest adminEditNoteRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = (String) jwt.getClaims().get("sub");

        NoteEntity noteEntity = noteJpaRepo.findById(noteId).orElseThrow(() -> new IllegalArgumentException("Note not found"));
        if (!noteEntity.getCreatedBy().equals(userId)) {throw new IllegalArgumentException("You are not permitted to edit this note");}
        noteEntity.setContent(adminEditNoteRequest.getContent());
        noteEntity.setTitle(adminEditNoteRequest.getTitle());
        noteEntity.setCreatedDate(LocalDateTime.now());
        noteJpaRepo.save(noteEntity);

        return new AdminEditNoteResponse(
                noteEntity.getTitle(),
                noteEntity.getContent(),
                noteEntity.getCreatedDate()
        );
    }
}
