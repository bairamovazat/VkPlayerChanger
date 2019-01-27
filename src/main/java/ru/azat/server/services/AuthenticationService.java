package ru.azat.server.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.azat.server.models.User;

import java.util.Optional;

public interface AuthenticationService {
    Optional<User> getCurrentUser();

}
