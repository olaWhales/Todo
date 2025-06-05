package com.app.todo.domain.model;


public record Note(
        Long id,
        String title,
        String content,
        boolean isPublic,
        String createdBy
) {}
