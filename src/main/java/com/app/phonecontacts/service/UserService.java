package com.app.phonecontacts.service;

import com.app.phonecontacts.model.entity.User;
import com.app.phonecontacts.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User readById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public User readByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    public User create(User user) {
        if (user != null && readByLogin(user.getLogin()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("USER");
            return userRepository.save(user);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with given login already exist!");
    }
}
