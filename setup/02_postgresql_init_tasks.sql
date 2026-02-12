CREATE TABLE tasks (
    task_id             BIGSERIAL PRIMARY KEY,
    user_id             BIGINT REFERENCES users(user_id),
    start_datetime      timestamp DEFAULT CURRENT_TIMESTAMP,
    end_datetime        timestamp DEFAULT CURRENT_TIMESTAMP,
    details             TEXT
);

INSERT INTO tasks (user_id, start_datetime, end_datetime, details)
VALUES (
    1
    , '2026-02-10 05:21:52.253525'
    , CURRENT_TIMESTAMP
    , 'Initial task entry'
);

