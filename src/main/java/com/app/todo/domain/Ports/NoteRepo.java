package com.app.todo.domain.Ports;

import com.app.todo.domain.model.Note;
import com.app.todo.domain.model.NoteEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo {
    Note save(Note note);

//    NoteEntity findByTitle(String title);
}
