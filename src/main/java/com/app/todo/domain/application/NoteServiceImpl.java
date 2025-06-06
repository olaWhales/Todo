package com.app.todo.domain.application;

import com.app.todo.api.dto.request.AdminCreateNoteRequest;
import com.app.todo.api.dto.response.AdminCreateNoteResponse;
import com.app.todo.domain.Ports.NoteRepo;
import com.app.todo.domain.Ports.NoteService;
import com.app.todo.domain.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepo noteRepo;

    @Override
    public AdminCreateNoteResponse createPublicNote(AdminCreateNoteRequest request, String username) {
        System.out.println("Received request: " + request);
        System.out.println("Username: " + username);
//        if (request.isPublic()) {
//            throw new IllegalArgumentException("Only public notes are allowed for this operation.");
//        }
        Note note = new Note(
                null,
                request.getTitle(),
                request.getContent(),
                true,
                username
        );
        System.out.println("this is note " + note);

        Note savedNote = noteRepo.save(note);
        System.out.println("this is from the repo "  + savedNote);
        AdminCreateNoteResponse response = new AdminCreateNoteResponse();
        response.setId(savedNote.id());
        response.setTitle(savedNote.title());
        response.setContent(savedNote.content());
        response.setPublic(savedNote.isPublic());
        response.setCreatedBy(savedNote.createdBy());

        return response;
    }
}
