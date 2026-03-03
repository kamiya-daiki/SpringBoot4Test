------------------------------
-- 画面モードマスタテーブル
------------------------------
CREATE TABLE screen_mode_master (
    -- 画面モードID
    screen_mode_id             INTEGER,
    -- 画面モード名
    screen_mode_name           VARCHAR(50) NOT NULL UNIQUE,

    -- 主キー設定
    PRIMARY KEY (screen_mode_id)
);

INSERT INTO screen_mode_master (screen_mode_id, screen_mode_name)
VALUES (
    0
    , 'AUTO'
);

INSERT INTO screen_mode_master (screen_mode_id, screen_mode_name)
VALUES (
    1
    , 'RIGHT'
);
INSERT INTO screen_mode_master (screen_mode_id, screen_mode_name)
VALUES (
    2
    , 'DARK'
);
