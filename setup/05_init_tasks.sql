------------------------------
-- タスクテーブル
------------------------------
CREATE TABLE tasks (
    -- ID
    id                  BIGSERIAL,
    -- タスクID
    task_id             BIGSERIAL,
    -- 概要
    summary             TEXT NOT NULL,
    -- 詳細
    details             TEXT,
    -- 開始日
    start_datetime      timestamp DEFAULT CURRENT_TIMESTAMP,
    -- 終了日
    end_datetime        timestamp DEFAULT CURRENT_TIMESTAMP,
    -- ユーザーテーブルID
    user_table_id       BIGSERIAL,

    -- 外部キー設定
    FOREIGN KEY (user_table_id) REFERENCES users (id) on delete cascade,

    -- 主キー設定    
    PRIMARY KEY (id)
);
