/* ユーザーテーブル初期データ登録 */
INSERT INTO m_user (
    user_id
  , password
  , user_name
  , role
) VALUES
('system@co.jp', '$2a$10$tahGBpAou3rIXA3IdO5YKuJU3YMG6b9/i62c.6knYZGFMYTb7ZgHW', 'システム管理者', 'ROLE_ADMIN')
, ('user1@co.jp', '$2a$10$tahGBpAou3rIXA3IdO5YKuJU3YMG6b9/i62c.6knYZGFMYTb7ZgHW', 'ユーザー1','ROLE_GENERAL')
;

/* 計算式テーブル初期データ登録 */
INSERT INTO formula (
    formula_name
  , year
  , month
  , day
) VALUES 
("1日後", 0, 0, 1)
, ("1年後の2ヶ月後の3日後", 1, 2, 3)
;