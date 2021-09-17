package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course.entities.Category;

/// jpa respository esta registado como componente do spring
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
