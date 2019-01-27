package ru.azat.server.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.azat.server.models.User;
import ru.azat.server.security.authentications.TokenAuthentication;
import ru.azat.server.security.details.UserDetailsImpl;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated() || !(authentication instanceof TokenAuthentication)) {
            return Optional.empty();
        }
        return Optional.ofNullable(((UserDetailsImpl) authentication.getDetails()).getUser());
    }

}
