package com.example.demo.utils;

import com.example.demo.entities.CartEntity;
import com.example.demo.entities.ItemEntity;
import com.example.demo.entities.UserEntity;
import java.math.BigDecimal;
import java.util.ArrayList;

public class GenerateCommon {

    public static UserEntity createUser() {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setUsername("UserName");
        user.setPassword("P@ssw0rd");
        user.setCart(new CartEntity());
        return user;
    }

    public static CartEntity createCart() {
        CartEntity cart = new CartEntity();
        cart.setId(1L);
        cart.setTotal(null);
        cart.setItems(new ArrayList<>());
        cart.setTotal(BigDecimal.valueOf(0.0));
        return cart;
    }

    public static ItemEntity createItem() {
        ItemEntity item = new ItemEntity();
        item.setId(1L);
        item.setName("New Item");
        item.setDescription("This is a new item");
        item.setPrice(BigDecimal.valueOf(100.0));
        return item;
    }



}
