package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}