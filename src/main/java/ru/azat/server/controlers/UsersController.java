package ru.azat.server.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.azat.server.forms.UserForm;
import ru.azat.server.models.User;
import ru.azat.server.security.authentications.TokenAuthentication;
import ru.azat.server.services.AuthenticationService;
import ru.azat.server.services.UsersService;
import ru.azat.server.transfer.UserDto;

import java.util.List;

import static ru.azat.server.transfer.UserDto.from;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationService service;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return from(usersService.findAll());
    }


    @PostMapping("/sign-up")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        usersService.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}