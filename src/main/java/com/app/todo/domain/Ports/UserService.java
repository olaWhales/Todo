package com.app.todo.domain.Ports;

import com.app.todo.domain.model.User;

public interface UserService {
    User createUser(User user);
}
