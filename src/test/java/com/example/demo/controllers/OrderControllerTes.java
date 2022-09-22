package com.example.demo.controllers;

import com.example.demo.entities.CartEntity;
import com.example.demo.entities.ItemEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.entities.UserOrderEntity;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.GenerateCommon;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class OrderControllerTes {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void submitSuccess() {
        UserEntity user = GenerateCommon.createUser();
        ItemEntity item = GenerateCommon.createItem();
        CartEntity cart = user.getCart();
        cart.setId(1L);
        cart.setTotal(BigDecimal.valueOf(0.0));
        cart.addItem(item);
        cart.setUser(user);
        user.setCart(cart);

        Mockito.when(userRepository.findByUsername("UserName")).thenReturn(user);

        ResponseEntity<UserOrderEntity> response = orderController.submit("UserName");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        UserOrderEntity userOrder = response.getBody();
        Assert.assertNotNull(userOrder);
        Assert.assertNotNull(userOrder.getItems());
        Assert.assertNotNull(userOrder.getUser());
        Assert.assertNotNull(userOrder.getTotal());
    }

    @Test
    public void submitWithoutUser() {
        UserEntity user = GenerateCommon.createUser();
        ItemEntity item = GenerateCommon.createItem();
        CartEntity cart = user.getCart();
        cart.setId(1L);
        cart.setTotal(BigDecimal.valueOf(0.0));
        cart.addItem(item);
        cart.setUser(user);
        user.setCart(cart);

        ResponseEntity<UserOrderEntity> response = orderController.submit("UserName");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getOrdersForUserSuccess() {
        UserEntity user = GenerateCommon.createUser();
        ItemEntity item = GenerateCommon.createItem();
        CartEntity cart = user.getCart();
        cart.setId(1L);
        cart.setTotal(BigDecimal.valueOf(0.0));
        cart.addItem(item);
        cart.setUser(user);
        user.setCart(cart);

        Mockito.when(userRepository.findByUsername("UserName")).thenReturn(user);

        ResponseEntity<List<UserOrderEntity>> response = orderController.getOrdersForUser(
                "UserName");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        List<UserOrderEntity> list = response.getBody();
        Assert.assertNotNull(list);
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void getOrdersForUserWithoutUser() {
        UserEntity user = GenerateCommon.createUser();
        ItemEntity item = GenerateCommon.createItem();
        CartEntity cart = user.getCart();
        cart.setId(1L);
        cart.setTotal(BigDecimal.valueOf(0.0));
        cart.addItem(item);
        cart.setUser(user);
        user.setCart(cart);

        ResponseEntity<List<UserOrderEntity>> response = orderController.getOrdersForUser(
                "UserName");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
