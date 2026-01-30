-- ログイン時更新
UPDATE users SET last_login_datetime = now() WHERE username = 'admin';


