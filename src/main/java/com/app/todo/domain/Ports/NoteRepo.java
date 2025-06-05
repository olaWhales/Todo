package com.app.todo.domain.Ports;

import com.app.todo.domain.model.Note;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo {
    Note save(Note note);
}
