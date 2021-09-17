package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course.entities.User;

/// jpa respository esta registado como componente do spring
public interface UserRepository extends JpaRepository<User,Long> {

}
