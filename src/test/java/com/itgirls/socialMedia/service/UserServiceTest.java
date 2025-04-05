package com.itgirls.socialMedia.service;

import com.itgirls.socialMedia.entity.User;
import com.itgirls.socialMedia.repository.UserRepository;
import com.itgirls.socialMedia.security.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserById_Success() throws Exception {
        User user = new User(11L, "Georg", "georg@mail.com", "11111", Role.ADMIN);
        Mockito.when(userRepository.getUserById(11L)).thenReturn(user);

        User userResult = userService.getUserById(11L);
        assertNotNull(userResult);
        assertEquals("Georg", user.getName());
        assertEquals("georg@mail.com", user.getEmail());
    }

    @Test
    void getUserById_unSuccess() {
        Mockito.when(userRepository.getUserById(12L)).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(EmptyResultDataAccessException.class, () -> userService.getUserById(12L));
    }
}