package com.app.todo.infrastructure;

import com.app.todo.domain.Ports.NoteRepo;
import com.app.todo.domain.model.Note;
import com.app.todo.domain.model.NoteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NoteDatabaseRepo implements NoteRepo {

    private final NoteJpaRepo noteJpaRepo;

    @Override
    public Note save(Note note) {

        NoteEntity entity = new NoteEntity(
                note.id(),
                note.title(),
                note.content(),
                note.isPublic(),
                note.createdBy()
        );
        NoteEntity saved = noteJpaRepo.save(entity);
        return new Note(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.isPublic(),
                saved.getCreatedBy()
        );
    }
}
