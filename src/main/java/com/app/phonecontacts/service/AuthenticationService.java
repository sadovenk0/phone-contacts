package com.app.phonecontacts.service;

import com.app.phonecontacts.auth.JwtProvider;
import com.app.phonecontacts.model.dto.user.UserRequest;
import com.app.phonecontacts.model.dto.utils.TokenResponse;
import com.app.phonecontacts.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    public final UserService userService;
    public final PasswordEncoder passwordEncoder;
    public final JwtProvider jwtProvider;

    public TokenResponse authenticate(UserRequest request) {
        var user = userService.readByLogin(request.getLogin())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not exist"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong password");
        }
        return new TokenResponse(jwtProvider.generateToken(request.getLogin()));
    }

    public TokenResponse registration(User user) {
        var userCreated = userService.create(user);
        return new TokenResponse(jwtProvider.generateToken(userCreated.getLogin()));
    }
}
