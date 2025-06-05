package com.app.todo.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteEntity {
    @Id
    private Long id;
    private String title;
    private String content;
    private boolean isPublic;
    private String createdBy;
}
