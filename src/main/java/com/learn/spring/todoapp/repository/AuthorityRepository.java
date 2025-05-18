package com.learn.spring.todoapp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorityRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuthorityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addAuthority(String username, String authority) {
        String sql = "INSERT INTO authorities (username, authority) VALUES (?, ?)";
        jdbcTemplate.update(sql, username, authority);
    }

    public void removeAuthority(String username, String authority) {
        String sql = "DELETE FROM authorities WHERE username = ? AND authority = ?";
        jdbcTemplate.update(sql, username, authority);
    }

    public void removeAllAuthorities(String username) {
        String sql = "DELETE FROM authorities WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }

    public List<String> findAuthoritiesByUsername(String username) {
        String sql = "SELECT authority FROM authorities WHERE username = ?";
        return jdbcTemplate.queryForList(sql, String.class, username);
    }
}
