package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.CartEntity;
import com.example.demo.entities.UserEntity;

import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
	CartEntity findByUser(UserEntity user);
}
