-- Users and Roles
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    role VARCHAR(50) NOT NULL
);

-- Customers
CREATE TABLE IF NOT EXISTS customers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    email VARCHAR(150)
);

-- Seed admin user (password: admin123)
INSERT INTO users (username, password)
VALUES ('admin', '$2a$10$5iA6Jp.MDg31t1NqxTe6AeaZizUlDIGvyOW0mbQj60LxbcTnyDYfK') -- bcrypt for "admin123"
ON CONFLICT (username) DO NOTHING;

INSERT INTO user_roles (user_id, role)
SELECT id, 'ROLE_USER' FROM users WHERE username = 'admin';
