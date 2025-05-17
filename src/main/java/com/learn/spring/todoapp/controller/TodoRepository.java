package com.learn.spring.todoapp.controller;

import com.learn.spring.todoapp.dto.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findByUsername(String username);
}
