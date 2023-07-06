package com.app.phonecontacts.controller;

import com.app.phonecontacts.model.dto.utils.TokenResponse;
import com.app.phonecontacts.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/registration")
    public Object registration() {
        // ToDo: add user creation
        return new Object();
    }

    @PostMapping("/login")
    public TokenResponse login() {
        // ToDo: add token generation
        return new TokenResponse();
    }
}
