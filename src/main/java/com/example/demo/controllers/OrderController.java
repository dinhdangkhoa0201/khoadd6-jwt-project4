package com.example.demo.controllers;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.UserEntity;
import com.example.demo.entities.UserOrderEntity;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;


    @PostMapping("/submit/{username}")
    public ResponseEntity<UserOrderEntity> submit(@PathVariable String username) {
        UserOrderEntity order = new UserOrderEntity();
        try {
            UserEntity user = userRepository.findByUsername(username);
            if (user == null) {
                log.error("UserName is not exist");
                return ResponseEntity.notFound().build();
            }
            order = UserOrderEntity.createFromCart(user.getCart());
            orderRepository.save(order);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/history/{username}")
    public ResponseEntity<List<UserOrderEntity>> getOrdersForUser(@PathVariable String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderRepository.findByUser(user));
    }
}
