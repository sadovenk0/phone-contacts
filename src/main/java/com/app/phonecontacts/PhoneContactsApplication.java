package com.app.phonecontacts;

import com.app.phonecontacts.model.entity.Contact;
import com.app.phonecontacts.model.entity.User;
import com.app.phonecontacts.repository.ContactRepository;
import com.app.phonecontacts.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class PhoneContactsApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    public static void main(String[] args) {
        SpringApplication.run(PhoneContactsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setLogin("mike");
        user1.setPassword("1111Mike");
        user1.setRole("USER");
        userRepository.save(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setLogin("nike");
        user2.setPassword("1111Nike");
        user2.setRole("USER");
        userRepository.save(user2);

        Contact contact = new Contact(
                1,
                "Name",
                "email1@mail.com,email2@mail.com",
                "+38999999999",
                user1
        );
        contactRepository.save(contact);
    }
}
