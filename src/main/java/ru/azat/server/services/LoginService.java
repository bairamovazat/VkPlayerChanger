package ru.azat.server.services;

import org.springframework.stereotype.Service;
import ru.azat.server.forms.LoginForm;
import ru.azat.server.transfer.TokenDto;

@Service
public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
