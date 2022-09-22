package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.UserEntity;
import com.example.demo.entities.UserOrderEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<UserOrderEntity, Long> {
	List<UserOrderEntity> findByUser(UserEntity user);
}
