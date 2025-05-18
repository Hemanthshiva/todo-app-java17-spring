package com.learn.spring.todoapp.service;

import com.learn.spring.todoapp.entity.User;
import com.learn.spring.todoapp.repository.AuthorityRepository;
import com.learn.spring.todoapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class UserInitializer {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInitializer(UserRepository userRepository, 
                          AuthorityRepository authorityRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        // Check if default user exists
        if (!userRepository.existsByUsername("user")) {
            // Create default user
            User user = new User(
                "user",
                passwordEncoder.encode("password"),
                "user@example.com"
            );
            userRepository.save(user);

            // Add authorities
            authorityRepository.addAuthority("user", "ROLE_USER");
            authorityRepository.addAuthority("user", "ROLE_ADMIN");

            System.out.println("Default user created: " + user.getUsername());
        }
    }
}
