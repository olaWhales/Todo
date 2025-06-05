package com.app.todo.domain.Ports;

import com.app.todo.domain.model.Note;
import org.springframework.stereotype.Component;

@Component
public interface NoteService {
    Note createPublicNote(Note note);
}
