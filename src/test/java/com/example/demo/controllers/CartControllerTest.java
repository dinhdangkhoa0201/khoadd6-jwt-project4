package com.example.demo.controllers;

import com.example.demo.entities.CartEntity;
import com.example.demo.entities.ItemEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.ModifyCartRequest;
import com.example.demo.utils.GenerateCommon;
import java.math.BigDecimal;
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

public class CartControllerTest {

    @InjectMocks
    private CartController cartController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CartRepository cartRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addToCartSuccess() {
        UserEntity user = GenerateCommon.createUser();
        ItemEntity item = GenerateCommon.createItem();
        CartEntity cart = user.getCart();
        cart.setId(1L);
        cart.setTotal(BigDecimal.valueOf(0.0));
        cart.addItem(item);
        cart.setUser(user);
        user.setCart(cart);

        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        Mockito.when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("UserName");
        request.setItemId(1L);
        request.setQuantity(2);

        ResponseEntity<CartEntity> response = cartController.addTocart(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        CartEntity entity = response.getBody();
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getItems());
        Assert.assertNotNull(entity.getUser());

        Assert.assertEquals(user.getUsername(), entity.getUser().getUsername());

        Mockito.verify(cartRepository, Mockito.times(1)).save(entity);
    }

    @Test
    public void addToCartWithoutUser() {
        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("UserName");
        request.setItemId(1L);
        request.setQuantity(2);

        ResponseEntity<CartEntity> response = cartController.addTocart(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void addToCartWithItems() {
        UserEntity user = GenerateCommon.createUser();
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("UserName");
        request.setItemId(1L);
        request.setQuantity(2);

        ResponseEntity<CartEntity> response = cartController.addTocart(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void removeToCartSuccess() {
        UserEntity user = GenerateCommon.createUser();
        ItemEntity item = GenerateCommon.createItem();
        CartEntity cart = user.getCart();
        cart.setId(1L);
        cart.setTotal(BigDecimal.valueOf(0.0));
        cart.addItem(item);
        cart.setUser(user);
        user.setCart(cart);

        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        Mockito.when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("UserName");
        request.setItemId(1L);
        request.setQuantity(2);

        ResponseEntity<CartEntity> response = cartController.removeFromcart(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        CartEntity entity = response.getBody();
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getItems());
        Assert.assertNotNull(entity.getUser());

        Assert.assertEquals(user.getUsername(), entity.getUser().getUsername());

        Mockito.verify(cartRepository, Mockito.times(1)).save(entity);
    }

    @Test
    public void removeToCartWithoutUser() {
        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("UserName");
        request.setItemId(1L);
        request.setQuantity(2);

        ResponseEntity<CartEntity> response = cartController.removeFromcart(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void removeToCartWithItems() {
        UserEntity user = GenerateCommon.createUser();
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("UserName");
        request.setItemId(1L);
        request.setQuantity(2);

        ResponseEntity<CartEntity> response = cartController.removeFromcart(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
