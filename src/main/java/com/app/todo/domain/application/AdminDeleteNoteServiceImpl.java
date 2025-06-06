package com.app.todo.domain.application;

import com.app.todo.api.dto.request.AdminDeleteNoteRequest;
import com.app.todo.api.dto.response.AdminDeleteNoteResponse;
import com.app.todo.domain.Ports.AdminDeleteNoteService;
import com.app.todo.domain.Ports.NoteRepo;
import com.app.todo.domain.model.NoteEntity;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminDeleteNoteServiceImpl implements AdminDeleteNoteService {
    private final NoteRepo noteRepo;

    @Override
    public AdminDeleteNoteResponse adminDeleteNoteResponse(AdminDeleteNoteRequest adminDeleteNoteRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = (String) jwt.getClaims().get("sub");

        NoteEntity noteEntity = noteRepo.findByTitle(adminDeleteNoteRequest.getTitle());
        if(noteEntity != null) {
            throw new IllegalStateException("Title not found");
        }


        return null;
    }
}
