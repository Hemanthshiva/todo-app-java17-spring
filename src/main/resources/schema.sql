-- Drop tables if they exist to avoid conflicts
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS todo;

-- Create todo table with SQLite-compatible syntax
CREATE TABLE IF NOT EXISTS todo (
    id INTEGER PRIMARY KEY,
    username TEXT NOT NULL,
    description TEXT NOT NULL,
    target_date TEXT NOT NULL,
    done INTEGER NOT NULL DEFAULT 0
);

-- Create a user's table with SQLite-compatible syntax
CREATE TABLE IF NOT EXISTS users (
    username TEXT PRIMARY KEY,
    password TEXT NOT NULL,
    email TEXT,
    enabled INTEGER NOT NULL DEFAULT 1
);

-- Create an authority's table with SQLite-compatible syntax
CREATE TABLE IF NOT EXISTS authorities (
    username TEXT NOT NULL,
    authority TEXT NOT NULL,
    FOREIGN KEY(username) REFERENCES users(username),
    UNIQUE(username, authority)
);
