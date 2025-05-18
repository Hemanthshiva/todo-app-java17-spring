-- Drop tables if they exist to avoid conflicts
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS todo;

-- Create todo table with SQLite-compatible syntax
CREATE TABLE IF NOT EXISTS todo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    target_date TEXT NOT NULL,
    done BOOLEAN NOT NULL DEFAULT 0
);

-- Create users table with SQLite-compatible syntax
CREATE TABLE IF NOT EXISTS users (
    username TEXT PRIMARY KEY,
    password TEXT NOT NULL,
    email TEXT,
    enabled INTEGER NOT NULL DEFAULT 1
);

-- Create authorities table with SQLite-compatible syntax
CREATE TABLE IF NOT EXISTS authorities (
    username TEXT NOT NULL,
    authority TEXT NOT NULL,
    FOREIGN KEY(username) REFERENCES users(username),
    UNIQUE(username, authority)
);
