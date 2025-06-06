package com.app.todo.domain.model;


import java.time.LocalDateTime;

public record Note(
        Long id,
        String title,
        String content,
        boolean isPublic,
        String createdBy,
        LocalDateTime createdDate
) {}
