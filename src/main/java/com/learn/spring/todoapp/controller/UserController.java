package com.learn.spring.todoapp.controller;

import com.learn.spring.todoapp.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class UserController {

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result, Model model) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "user.confirmPassword.mismatch", "Passwords do not match");
        }

        if (userDetailsManager.userExists(userDto.getUsername())) {
            result.rejectValue("username", "user.username.exists", "Username already exists");
        }
        
        // Basic email validation (can be enhanced)
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty() || !userDto.getEmail().contains("@")) {
            result.rejectValue("email", "user.email.invalid", "Invalid email format");
        }

        if (result.hasErrors()) {
            return "register";
        }

        UserDetails user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles("USER") // Assign a default role
                .build();
        userDetailsManager.createUser(user);

        // Optionally, you can add a success message
        // model.addAttribute("registrationSuccess", true);
        return "redirect:/login?registerSuccess";
    }
}