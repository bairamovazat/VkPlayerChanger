package ru.azat.server.services;

import org.springframework.stereotype.Service;
import ru.azat.server.forms.UserForm;
import ru.azat.server.models.User;

import java.util.List;

@Service
public interface UsersService {
    void signUp(UserForm userForm);

    List<User> findAll();

    User findOne(Long userId);
}
