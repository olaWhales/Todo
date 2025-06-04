package com.app.todo.domain.model;

import java.util.UUID;

public record User(
        UUID id,
        String name
) {
}
