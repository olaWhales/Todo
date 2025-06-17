package com.app.todo.infrastructure;

import com.app.todo.domain.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.UUID;

public interface NoteJpaRepo extends JpaRepository<NoteEntity, Long> {
    NoteEntity findByTitle(String title);

    Arrays findAllByCreatedBy(String userId);
}
