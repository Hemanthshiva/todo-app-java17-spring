package com.learn.spring.todoapp.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.learn.spring.todoapp.entity.Todo;
import com.learn.spring.todoapp.repository.TodoRepository;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

    private final TodoRepository todoRepository;

    public TodoControllerJpa(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        String username = getLoggedInUsername();
        List<Todo> todos = todoRepository.findByUsername(username);
        model.addAttribute("todos", todos);

        // Add logging statements
        System.out.println("Username: " + username);
        System.out.println("Todos: " + todos);

        return "listTodos";
    }

    @GetMapping("add-todo")
    public String showNewTodoPage(ModelMap model) {
        String username = getLoggedInUsername();
        Todo todo = new Todo(0, username, "", LocalDate.now().plusMonths(11), false);
        model.put("todo", todo);
        return "todo";
    }

    @PostMapping("add-todo")
    public String addNewTodo(@Valid Todo todo, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "todo";
        }
        String username = getLoggedInUsername();
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

    @GetMapping("delete-todo")
    public String deleteTodo(@RequestParam Integer id) {
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @GetMapping("update-todo")
    public String showUpdateTodoPage(@RequestParam Integer id, ModelMap model) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo Id:" + id));
        model.addAttribute("todo", todo);
        return "todo";
    }

    @PostMapping("update-todo")
    public String updateTodo(@Valid Todo todo, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "todo";
        }
        String username = getLoggedInUsername();
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
