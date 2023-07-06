package com.app.phonecontacts.service;

import com.app.phonecontacts.auth.JwtProvider;
import com.app.phonecontacts.model.dto.user.UserRequest;
import com.app.phonecontacts.model.dto.utils.AuthenticationRequest;
import com.app.phonecontacts.model.dto.utils.TokenResponse;
import com.app.phonecontacts.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthenticationService {
    public final UserService userService;
    public final UserDetailsServiceImpl userDSI;
    public final PasswordEncoder passwordEncoder;
    public final JwtProvider jwtProvider;

    public TokenResponse authenticate(UserRequest request) {
        UserDetails userDetails = userDSI.loadUserByUsername(request.getLogin());
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong password");
        }
        return new TokenResponse(jwtProvider.generateToken(request.getLogin()));
    }

    public TokenResponse registration(User user) {
        var userCreated = userService.create(user);
        return new TokenResponse(jwtProvider.generateToken(userCreated.getLogin()));
    }
}
