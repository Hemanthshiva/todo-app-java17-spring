package com.learn.spring.todoapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager; // Added import
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SpringSecurityConfiguration {

    // DataSource field and constructor injection removed as DataSource will be injected directly into the bean method.

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) { // DataSource injected as method parameter
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        return users;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/login", "/register", "/webjars/**", "/css/**", "/js/**", "/images/**", "/favicon.ico").permitAll()
            .anyRequest().authenticated()
        );
        
        http.formLogin(form -> form
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/", true)
            .failureUrl("/login?error=true")
        );
        
        http.csrf(csrf -> csrf.disable());
        
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
        
        return http.build();
    }
}
