------------------------------
-- ユーザーテーブル
------------------------------
CREATE TABLE users (
    -- ユーザーID
    user_id                 BIGSERIAL,
    -- ユーザーID履歴
    user_id_history         BIGSERIAL,
    -- メールアドレス
    email                   VARCHAR(50) NOT NULL,
    -- パスワード(BCryptでハッシュ化済)
    user_password           VARCHAR(50) NOT NULL,
    -- ユーザー作成日時
    create_datetime                 timestamp DEFAULT CURRENT_TIMESTAMP,
    -- 最終パスワード更新日時
    last_update_password_datetime   timestamp DEFAULT CURRENT_TIMESTAMP,
    -- 最終ログイン日時
    last_login_datetime             timestamp DEFAULT CURRENT_TIMESTAMP,
    -- 権限
    authority     integer NOT NULL DEFAULT 0,
    -- 画面モード
    screen_mode   integer NOT NULL DEFAULT 0,

    -- 外部キー設定
    FOREIGN KEY (authority) REFERENCES authority_Master(authority_id),
    FOREIGN KEY (screen_mode) REFERENCES screen_mode_Master(screen_mode_id),

    -- 主キー設定
    PRIMARY KEY (user_id, user_id_history)
);
