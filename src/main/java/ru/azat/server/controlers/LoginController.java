package ru.azat.server.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.azat.forms.LoginForm;
import ru.azat.services.LoginService;
import ru.azat.transfer.TokenDto;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm) {
        int i = 0;
        return ResponseEntity.ok(loginService.login(loginForm));
    }
}