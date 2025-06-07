package com.app.todo.domain.application;

import com.app.todo.dto.request.AdminCreateNoteRequest;
import com.app.todo.dto.response.AdminCreateNoteResponse;
import com.app.todo.domain.Ports.NoteRepo;
import com.app.todo.domain.Ports.AdminCreateNoteService;
import com.app.todo.domain.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminCreateNoteServiceImpl implements AdminCreateNoteService {

    private final NoteRepo noteRepo;

    @Override
    public AdminCreateNoteResponse createPublicNote(AdminCreateNoteRequest request, String userId) {
        System.out.println("Received request: " + request);
        System.out.println("Username: " + userId);
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
        AdminCreateNoteResponse response = new AdminCreateNoteResponse();
        response.setId(savedNote.id());
        response.setTitle(savedNote.title());
        response.setContent(savedNote.content());
        response.setPublic(savedNote.isPublic());
        response.setCreatedBy(savedNote.createdBy());

        return response;
    }
}
