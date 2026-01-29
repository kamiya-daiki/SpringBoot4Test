CREATE TABLE users (
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(50) NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,  -- BCrypt でハッシュ化されたパスワード
    enabled     BOOLEAN DEFAULT TRUE,
    create_datetime         timestamp DEFAULT CURRENT_TIMESTAMP,
    last_login_datetime     timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, password enabled)
VALUES (
    'admin'
    , '}$2a$10$Dow1xgFQzvQKZy1E9K8b9e0ZK3cZPjvF1qJ1nPzZ6l7YpJ1pQeW8C'    -- "password"
    , TRUE
    , CURRENT_TIMESTAMP
    , CURRENT_TIMESTAMP
);

