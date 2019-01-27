package ru.azat.server.services;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.azat.server.forms.LoginForm;
import ru.azat.server.models.Token;
import ru.azat.server.models.User;
import ru.azat.server.repositories.TokenRepository;
import ru.azat.server.repositories.UserRepository;
import ru.azat.server.transfer.TokenDto;

import java.util.Optional;

import static ru.azat.server.transfer.TokenDto.from;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TokenDto login(LoginForm loginForm) {
        Optional<User> userCandidate = userRepository.findOneByLogin(loginForm.getLogin());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            if (passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                Token token = Token.builder()
                        .user(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokenRepository.save(token);
                return from(token);
            }
        }
        throw new IllegalArgumentException("User not found");
    }

}
