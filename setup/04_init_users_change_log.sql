------------------------------
-- ユーザー変更履歴テーブル
------------------------------
CREATE TABLE users_change_log (
    -- ID
    id                      BIGSERIAL,
    -- 変更履歴ID
    change_log_id           BIGSERIAL,
    -- ユーザーテーブルID
    user_table_id           BIGSERIAL,
    -- ユーザーID
    user_id                 BIGSERIAL,
    -- メールアドレス
    email                   VARCHAR(50) NOT NULL,
    -- パスワード(BCryptでハッシュ化済)
    user_password           VARCHAR(50) NOT NULL,
    -- ユーザー作成日時
    create_datetime         timestamp DEFAULT CURRENT_TIMESTAMP,
    -- 最終パスワード更新日時
    last_update_password_datetime   timestamp DEFAULT CURRENT_TIMESTAMP,
    -- 最終ログイン日時
    last_login_datetime     timestamp DEFAULT CURRENT_TIMESTAMP,
    -- 権限
    authority               integer NOT NULL DEFAULT 0,
    -- 画面モード
    screen_mode             integer NOT NULL DEFAULT 0,

    -- 変更日時
    change_datetime         timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    -- 変更ユーザーID
    change_user_id          BIGSERIAL NOT NULL,
    -- 変更理由
    change_reason           TEXT NOT NULL,
    

    -- 外部キー設定
    FOREIGN KEY (authority) REFERENCES authority_Master(authority_id),
    FOREIGN KEY (screen_mode) REFERENCES screen_mode_Master(screen_mode_id),
    FOREIGN KEY (user_table_id) REFERENCES users(id),
    FOREIGN KEY (change_user_id) REFERENCES users(id),

    -- 主キー設定
    PRIMARY KEY (id)
);
