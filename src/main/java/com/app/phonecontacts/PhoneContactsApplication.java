package com.app.phonecontacts;

import com.app.phonecontacts.model.entity.User;
import com.app.phonecontacts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneContactsApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    @Autowired
    public PhoneContactsApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PhoneContactsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setId(1L);
        user.setLogin("mike");
        user.setPassword("1111Mike");
        user.setRole("USER");
        userRepository.save(user);
    }
}
