package com.app.todo.domain.application;

import com.app.todo.domain.Ports.UserViewAllNotesService;
import com.app.todo.domain.model.NoteEntity;
import com.app.todo.infrastructure.NoteJpaRepo;
import com.app.todo.dto.response.UserViewNoteResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserViewAllNotesServiceImpl implements UserViewAllNotesService {
    private final NoteJpaRepo noteJpaRepo;

    @Override
    public List<UserViewNoteResponse> viewAllNotes(String userId) {
//        return noteJpaRepo.findAllByCreatedBy(userId).stream()
//                .map(note -> {
//                    UserViewNoteResponse response = new UserViewNoteResponse();
//                    response.setTitle(note.getTitle());
//                    response.setContent(note.getContent());
//                    response.setPublic(note.isPublic());
//                    response.setCreatedBy(note.getCreatedBy());
//                    return response;
//                })
//                .collect(Collectors.toList());
        return List.of();
    }
}