------------------------------
-- タスクテーブル
------------------------------
CREATE TABLE tasks (
    -- タスクID
    task_id             BIGSERIAL,
    -- タスクID履歴
    task_id_history     BIGSERIAL,
    -- 概要
    summary             TEXT NOT NULL,
    -- 詳細
    details             TEXT,
    -- 開始日
    start_datetime      timestamp DEFAULT CURRENT_TIMESTAMP,
    -- 終了日
    end_datetime        timestamp DEFAULT CURRENT_TIMESTAMP,

    -- 外部キー設定
    user_id                  BIGSERIAL,
    user_id_history          BIGSERIAL,
    FOREIGN KEY (user_id, user_id_history) REFERENCES users (user_id, user_id_history),

    -- 主キー設定    
    PRIMARY KEY (task_id, task_id_history)
);
