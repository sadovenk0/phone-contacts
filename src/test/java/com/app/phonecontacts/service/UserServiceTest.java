package com.app.phonecontacts.service;

import com.app.phonecontacts.exception.NullEntityReferenceException;
import com.app.phonecontacts.model.entity.User;
import com.app.phonecontacts.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    class Create {
        @Test
        void should_ReturnUser_When_InputIsValid() {
            User expected = new User();
            when(userRepository.save(expected)).thenReturn(expected);

            User actual = userService.create(expected);

            assertEquals(expected, actual);
        }

        @Test
        void should_ThrowsException_When_InputIsNull() {
            when(userRepository.save(null)).thenThrow(IllegalArgumentException.class);

            Executable executable = () -> userService.create(null);

            assertThrows(NullEntityReferenceException.class, executable);
        }
    }


}
