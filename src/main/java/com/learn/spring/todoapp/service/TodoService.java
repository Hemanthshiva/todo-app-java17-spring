package com.learn.spring.todoapp.service;

import com.learn.spring.todoapp.dto.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static int todosCount = 0;
    private static final List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(++todosCount, "Automation", "Learn AWS", LocalDate.now().plusMonths(3), false));
        todos.add(new Todo(++todosCount, "Automation", "Learn Azure", LocalDate.now().plusMonths(6), false));
        todos.add(new Todo(++todosCount, "Hemanth", "Learn Automation", LocalDate.now().plusMonths(2), false));
    }

    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean isDone) {
        Todo todo = new Todo(++todosCount, username, description, targetDate, isDone);
        todos.add(todo);
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().get();
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
