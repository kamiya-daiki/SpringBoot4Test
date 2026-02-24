------------------------------
-- 権限マスタテーブル
------------------------------
CREATE TABLE authority_Master (
    -- 権限ID
    authority_id             INTEGER PRIMARY KEY,
    -- 権限名
    authority_name           VARCHAR(50) NOT NULL UNIQUE
);

-- 一般（ユーザー）権限
INSERT INTO authority_Master (authority_id, authority_name)
VALUES (
    0
    , 'USER'
);

-- 管理者権限
INSERT INTO authority_Master (authority_id, authority_name)
VALUES (
    1
    , 'ADMIN'
);
