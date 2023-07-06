package com.app.phonecontacts.controller;

import com.app.phonecontacts.model.dto.user.UserMapper;
import com.app.phonecontacts.model.dto.user.UserRequest;
import com.app.phonecontacts.model.dto.utils.TokenResponse;
import com.app.phonecontacts.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    public final AuthenticationService service;
    public final UserMapper userMapper;

    @PostMapping("/registration")
    public TokenResponse registration(@RequestBody UserRequest request) {
        return service.registration(userMapper.userRequestToUser(request));
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody UserRequest request) {
        return service.authenticate(request);
    }




}
