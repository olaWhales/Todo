package com.app.todo.infrastructure;

import com.app.todo.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepo extends JpaRepository<UserEntity, UUID>{
}
