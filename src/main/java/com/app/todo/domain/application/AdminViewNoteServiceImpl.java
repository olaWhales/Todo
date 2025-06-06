package com.app.todo.domain.application;

import com.app.todo.api.dto.request.AdminViewNoteRequest;
import com.app.todo.api.dto.response.AdminViewNoteResponse;
import com.app.todo.domain.Ports.AdminViewNoteService;
import com.app.todo.domain.Ports.NoteRepo;
import com.app.todo.domain.model.NoteEntity;
import com.app.todo.infrastructure.NoteJpaRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
//@NoArgsConstructor
public class AdminViewNoteServiceImpl implements AdminViewNoteService {
    private NoteJpaRepo noteJpaRepo;

    @Override
    public AdminViewNoteResponse adminViewNote(AdminViewNoteRequest adminViewNoteRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = jwt.getClaimAsString("sub"); // Usually the 'sub' or 'preferred_username' claim


        System.out.println("Trying to fetch note with title: " + adminViewNoteRequest.getTitle());
        NoteEntity noteEntity = noteJpaRepo.findByTitle(adminViewNoteRequest.getTitle());
        if(noteEntity == null) {
            throw new IllegalStateException("Note not found");
        }
        System.out.println("Logged in user: " + userId);
        System.out.println("Note createdBy: " + noteEntity.getCreatedBy());

        if(!noteEntity.getCreatedBy().equals(userId)) {
            throw new IllegalStateException("You are not allowed to view this note");
        }
        AdminViewNoteResponse adminViewNoteResponse = new AdminViewNoteResponse();
        adminViewNoteResponse.setTitle(noteEntity.getTitle());
        adminViewNoteResponse.setContent(noteEntity.getContent());
        adminViewNoteResponse.setPublic(noteEntity.isPublic());
        adminViewNoteResponse.setCreatedBy(noteEntity.getCreatedBy());
        return adminViewNoteResponse;
    }
}
