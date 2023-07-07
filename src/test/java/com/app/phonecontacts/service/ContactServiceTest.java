package com.app.phonecontacts.service;

import com.app.phonecontacts.exception.NullEntityReferenceException;
import com.app.phonecontacts.model.entity.Contact;
import com.app.phonecontacts.model.entity.User;
import com.app.phonecontacts.repository.ContactRepository;
import com.app.phonecontacts.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactServiceTest {
    @Mock
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @InjectMocks
    @Autowired
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    void setUpBeforeAll(){
        var user = User.builder().login("vasyl").password("1111Vasyl").role("USER").build();
        userRepository.save(user);
    }

    @Nested
    class Create {
        @Test
        void should_ReturnContact_When_InputIsValid() {
            Contact expected = Contact.builder().name("Mykhailo").build();
            when(contactRepository.save(expected)).thenReturn(expected);

            Contact actual = contactService.create(expected,1);

            assertEquals(expected, actual);
        }

        @Test
        void should_ThrowsException_When_InputIsNull() {
            when(contactRepository.save(null)).thenThrow(IllegalArgumentException.class);

            Executable executable = () -> contactService.create(null,1);

            assertThrows(NullEntityReferenceException.class, executable);
        }
    }

    @Nested
    class Read {
        @ParameterizedTest
        @ValueSource(longs = {1})
        void should_ThrowsException_When_ContactNotExists(long id) {
            when(contactRepository.findById(anyLong())).thenThrow(EntityNotFoundException.class);

            Executable executable = () -> contactService.readById(id);

            assertThrows(EntityNotFoundException.class, executable);
        }
    }


}
