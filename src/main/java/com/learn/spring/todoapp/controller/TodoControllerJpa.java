package com.learn.spring.todoapp.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.learn.spring.todoapp.entity.Todo;
import com.learn.spring.todoapp.entity.User;
import com.learn.spring.todoapp.repository.TodoRepository;
import com.learn.spring.todoapp.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoControllerJpa(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        String username = getLoggedInUsername();
        List<Todo> todos = todoRepository.findByUsername(username);
        model.addAttribute("todos", todos);
        return "listTodos";
    }

    @GetMapping("add-todo")
    public String showNewTodoPage(ModelMap model) {
        String username = getLoggedInUsername();
        Todo todo = new Todo(0, username, "", LocalDate.now().plusMonths(1), false);
        model.put("todo", todo);
        return "todo";
    }

    @PostMapping("add-todo")
    public String addNewTodo(@Valid Todo todo, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "todo";
        }

        String username = getLoggedInUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found: " + username));

        todo.setUser(user); // This will also set the username field
        todoRepository.save(todo);

        return "redirect:list-todos";
    }

    @GetMapping("delete-todo")
    public String deleteTodo(@RequestParam Integer id) {
        // Verify the todo belongs to the current user before deleting
        String username = getLoggedInUsername();
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo Id:" + id));

        if (!todo.getUsername().equals(username)) {
            throw new IllegalStateException("Not authorized to delete this todo");
        }

        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @GetMapping("update-todo")
    public String showUpdateTodoPage(@RequestParam Integer id, ModelMap model) {
        // Verify the todo belongs to the current user
        String username = getLoggedInUsername();
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo Id:" + id));

        if (!todo.getUsername().equals(username)) {
            throw new IllegalStateException("Not authorized to update this todo");
        }

        model.addAttribute("todo", todo);
        return "todo";
    }

    @PostMapping("update-todo")
    public String updateTodo(@Valid Todo todo, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "todo";
        }

        String username = getLoggedInUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found: " + username));

        // Verify the todo belongs to the current user
        Todo existingTodo = todoRepository.findById(todo.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo Id:" + todo.getId()));

        if (!existingTodo.getUsername().equals(username)) {
            throw new IllegalStateException("Not authorized to update this todo");
        }

        todo.setUser(user); // This will also set the username field
        todoRepository.save(todo);

        return "redirect:list-todos";
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
