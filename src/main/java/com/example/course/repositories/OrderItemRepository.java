package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course.entities.OrderItem;

/// jpa respository esta registado como componente do spring
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
