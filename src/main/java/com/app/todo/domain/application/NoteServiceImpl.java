package com.app.todo.domain.application;

import com.app.todo.domain.Ports.NoteRepo;
import com.app.todo.domain.Ports.NoteService;
import com.app.todo.domain.model.Note;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

private final NoteRepo noteRepo;

@Override
public Note createPublicNote(Note note) {
    if (!note.isPublic()) {
        throw new IllegalArgumentException("Only public notes are allowed for this operation.");
    }
    return noteRepo.save(new Note(
            note.id(),
            note.title(),
            note.content(),
            true,
            note.createdBy()
    ));
}
}
