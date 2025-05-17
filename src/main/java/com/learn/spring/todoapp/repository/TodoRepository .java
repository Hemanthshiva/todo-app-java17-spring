// package com.learn.spring.todoapp.repository;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import com.learn.spring.todoapp.dto.Todo;

// @Repository
// public interface TodoRepository extends JpaRepository<Todo, Integer> {
//     List<Todo> findByUsername(String username);

//     void deleteById(Long id);

//     Optional<Todo> findById(Long id);

// }