------------------------------
-- ボタン権限マスタテーブル
------------------------------
CREATE TABLE button_authority_Master (
    -- ボタンID
    button_authority          BIGSERIAL,
    -- ボタン表示名
    button_label_jp           VARCHAR(50) NOT NULL,
    -- ボタンアクションフォーム
    button_action           VARCHAR(50) NOT NULL,
    -- 権限
    authority               Integer NOT NULL DEFAULT 0,

    -- 主キー設定
    PRIMARY KEY (button_authority),

    -- 外部キー設定
    FOREIGN KEY (authority) REFERENCES authority_Master(authority_id)
);

INSERT INTO button_authority_Master (button_authority, button_label_jp, button_action, authority)
VALUES (
    1
    , '管理者画面'
    , '/admin'
    , 1
);

