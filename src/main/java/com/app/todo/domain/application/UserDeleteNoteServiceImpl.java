package com.app.todo.domain.application;

import com.app.todo.Utilities.ApplicationUtility;
import com.app.todo.dto.response.UserDeleteNoteResponse;
import com.app.todo.domain.Ports.UserDeleteNoteService;
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
public class UserDeleteNoteServiceImpl implements UserDeleteNoteService {
    private final NoteJpaRepo noteJpaRepo;

    @Override
    public UserDeleteNoteResponse adminDeleteNoteResponse(Long noteId) {
        String userId = ApplicationUtility.userAuthentication();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Jwt jwt = (Jwt) authentication.getPrincipal();
//        String userId = (String) jwt.getClaims().get(SUB_USER);

        NoteEntity noteEntity = noteJpaRepo.findById(noteId).orElseThrow(()-> new IllegalArgumentException(TITLE_NOT_FOUND));
        if(!noteEntity.getCreatedBy().equals(userId)) {throw new IllegalArgumentException(USER_NOT_FOUND);}
        noteJpaRepo.delete(noteEntity);
        UserDeleteNoteResponse userDeleteNoteResponse = new UserDeleteNoteResponse();
        userDeleteNoteResponse.setMessage(NOTE + noteEntity.getTitle() + SUCCESSFUL_DELETION);

        return userDeleteNoteResponse;
    }
}
