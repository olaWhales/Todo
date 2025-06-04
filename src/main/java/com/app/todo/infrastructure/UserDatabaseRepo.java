package com.app.todo.infrastructure;

import com.app.todo.domain.model.User;
import com.app.todo.domain.Ports.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDatabaseRepo implements UserRepo {

//    private final UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        return null;
    }
}
