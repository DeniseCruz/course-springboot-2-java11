package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course.entities.Order;

/// jpa respository esta registado como componente do spring
public interface OrderRepository extends JpaRepository<Order,Long> {

}
