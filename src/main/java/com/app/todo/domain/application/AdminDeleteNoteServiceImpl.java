package com.app.todo.domain.application;

import com.app.todo.dto.request.AdminDeleteNoteRequest;
import com.app.todo.dto.response.AdminDeleteNoteResponse;
import com.app.todo.domain.Ports.AdminDeleteNoteService;
import com.app.todo.domain.model.NoteEntity;
import com.app.todo.infrastructure.NoteJpaRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminDeleteNoteServiceImpl implements AdminDeleteNoteService {
    private final NoteJpaRepo noteJpaRepo;

    @Override
    public AdminDeleteNoteResponse adminDeleteNoteResponse(AdminDeleteNoteRequest adminDeleteNoteRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = (String) jwt.getClaims().get("sub");

        NoteEntity noteEntity = noteJpaRepo.findByTitle(adminDeleteNoteRequest.getTitle());
        if(noteEntity == null) {throw new IllegalArgumentException("Title not found");}
        if(!noteEntity.getCreatedBy().equals(userId)) {throw new IllegalArgumentException("User not found");}
        noteJpaRepo.delete(noteEntity);
        AdminDeleteNoteResponse adminDeleteNoteResponse = new AdminDeleteNoteResponse();
        adminDeleteNoteResponse.setMessage("Note" + noteEntity.getTitle() + " deleted successfully");

        return adminDeleteNoteResponse;
    }
}
