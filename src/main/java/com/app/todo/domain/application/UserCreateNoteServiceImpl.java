package com.app.todo.domain.application;

import com.app.todo.dto.request.UserCreateNoteRequest;
import com.app.todo.dto.response.UserCreateNoteResponse;
import com.app.todo.domain.Ports.NoteRepo;
import com.app.todo.domain.Ports.UserCreateNoteService;
import com.app.todo.domain.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserCreateNoteServiceImpl implements UserCreateNoteService {

    private final NoteRepo noteRepo;

    @Override
    public UserCreateNoteResponse createPublicNote(UserCreateNoteRequest request, String userId) {
//        System.out.println("Received request: " + request);
//        System.out.println("Username: " + userId);
//        if (request.isPublic()) {
//            throw new IllegalArgumentException("Only public notes are allowed for this operation.");
//        }
        Note note = new Note(
                null,
                request.getTitle(),
                request.getContent(),
                true,
                userId,
                LocalDateTime.now()
        );
// this is where the note save

        Note savedNote = noteRepo.save(note);
        UserCreateNoteResponse response = new UserCreateNoteResponse();
        response.setId(savedNote.id());
        response.setTitle(savedNote.title());
        response.setContent(savedNote.content());
        response.setPublic(savedNote.isPublic());
        response.setCreatedBy(savedNote.createdBy());

        return response;
    }
}
