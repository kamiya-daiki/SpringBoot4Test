------------------------------
-- 画面モードマスタテーブル
------------------------------
CREATE TABLE screen_mode_Master (
    -- 画面モードID
    screen_mode_id             INTEGER PRIMARY KEY,
    -- 画面モード名
    screen_mode_name           VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO screen_mode_Master (screen_mode_id, screen_mode_name)
VALUES (
    0
    , 'DEFAULT'
);

INSERT INTO screen_mode_Master (screen_mode_id, screen_mode_name)
VALUES (
    1
    , 'RIGHT'
);
INSERT INTO screen_mode_Master (screen_mode_id, screen_mode_name)
VALUES (
    2
    , 'DARK'
);
