------------------------------
-- タスク変更履歴テーブル
------------------------------
CREATE TABLE tasks_change_log (
    -- ID
    id                  BIGSERIAL,
    -- タスク変更履歴ID
    task_change_log_id  BIGSERIAL,
    -- タスクテーブルID
    task_table_id       BIGSERIAL,
    -- 概要
    summary             TEXT NOT NULL,
    -- 詳細
    details             TEXT,
    -- 開始日
    start_datetime      timestamp DEFAULT CURRENT_TIMESTAMP,
    -- 終了日
    end_datetime        timestamp DEFAULT CURRENT_TIMESTAMP,
    
    -- 変更日時
    change_datetime         timestamp DEFAULT CURRENT_TIMESTAMP,
    -- ユーザーテーブルID
    change_user_table_id    BIGSERIAL,
    -- 変更理由
    change_reason           TEXT NOT NULL,

    -- 外部キー設定
    FOREIGN KEY (task_change_log_id) REFERENCES tasks (id) on delete cascade,
    FOREIGN KEY (change_user_table_id) REFERENCES users (id) on delete cascade,

    -- 主キー設定    
    PRIMARY KEY (id)
);
