package com.learn.spring.todoapp.config;

import com.learn.spring.todoapp.repository.AuthorityRepository;
import com.learn.spring.todoapp.repository.UserRepository;
import com.learn.spring.todoapp.service.UserInitializer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    /**
     * Create a no-op UserInitializer for tests to prevent database initialization
     */
    @Bean
    @Primary
    public UserInitializer userInitializer(UserRepository userRepository, 
                                          AuthorityRepository authorityRepository,
                                          PasswordEncoder passwordEncoder) {
        return new UserInitializer(userRepository, authorityRepository, passwordEncoder) {
            @Override
            public void init() {
                // Do nothing in tests
                System.out.println("Test UserInitializer: Skipping database initialization");
            }
        };
    }
}
