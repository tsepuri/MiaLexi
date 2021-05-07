package com.csds393.mialexi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.csds393.mialexi.controller.UsersController;
import com.csds393.mialexi.model.User;
import com.csds393.mialexi.repository.UserJpaRepository;

class UsersControllerTest {
    @InjectMocks
    private UsersController userController;

    @Mock
    private UserJpaRepository userJpaRepository;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testRegisterLevel() {
        User user = new User();
        user.setUsername("demo");
        user.setPassword("demo123");
        userController.register(user);
        assertEquals(1, user.getLevel());
    }
    @Test
    void testRegisterLevel2() {
        User user = new User();
        user.setUsername("demo");
        user.setPassword("demo123");
        User user1 = new User();
        user1.setUsername("demo");
        user1.setPassword("demo123");
        userController.register(user);
        when(userJpaRepository.findByUsername(user.getUsername())).thenReturn(user);
        assertEquals(false, userController.register(user1));
    }

    @Test
    void testfindLevel() {
        User user = new User();
        user.setUsername("demo");
        user.setPassword("demo123");
        userController.register(user);
        when(userJpaRepository.findByUsername(user.getUsername())).thenReturn(user);
        assertEquals(1, userController.findLevel(user.getUsername()));
    }
}