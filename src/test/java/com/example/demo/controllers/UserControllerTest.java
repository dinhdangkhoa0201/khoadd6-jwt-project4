package com.example.demo.controllers;

import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.CreateUserRequest;
import com.example.demo.utils.GenerateCommon;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createUserSuccess() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("UserName");
        request.setPassword("P@ssw0rd");
        request.setConfirmPassword("P@ssw0rd");

        ResponseEntity<UserEntity> response = userController.createUser(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        UserEntity user = response.getBody();
        Assert.assertNotNull(user);
        Assert.assertEquals(request.getUsername(), user.getUsername());
    }

    @Test
    public void findByIdSuccess() {
        UserEntity user = GenerateCommon.createUser();
        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        ResponseEntity<UserEntity> response = userController.findById(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(user, response.getBody());
    }

    @Test
    public void findByIdFailed() {
        ResponseEntity<UserEntity> response = userController.findById(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void findByUsernameSuccess() {
        UserEntity user = GenerateCommon.createUser();
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        ResponseEntity<UserEntity> response = userController.findByUserName("UserName");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        UserEntity entity = response.getBody();
        Assert.assertEquals(user.getId(), entity.getId());
        Assert.assertEquals(user.getUsername(), entity.getUsername());
        Assert.assertEquals(user.getCart(), entity.getCart());
    }

    @Test
    public void findByUsernameFailed() {
        ResponseEntity<UserEntity> response = userController.findByUserName("UserName");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
