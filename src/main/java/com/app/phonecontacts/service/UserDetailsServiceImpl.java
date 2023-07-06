package com.app.phonecontacts.service;

import com.app.phonecontacts.model.entity.User;
import com.app.phonecontacts.model.security.UserDetailsImpl;
import com.app.phonecontacts.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(s);

        return new UserDetailsImpl(user.orElseThrow(
                () -> new UsernameNotFoundException("User not found")));
    }
}
