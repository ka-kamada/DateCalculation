/* ユーザーテーブル作成 */
CREATE TABLE IF NOT EXISTS m_user (
    user_id VARCHAR(50) PRIMARY KEY
  , password VARCHAR(100)
  , user_name VARCHAR(50)
  , role VARCHAR(50)
) DEFAULT CHARSET=utf8;

/* 計算式テーブル作成 */
CREATE TABLE IF NOT EXISTS formula ( 
  formula_id INTEGER(5) NOT NULL AUTO_INCREMENT
  ,formula_name VARCHAR(50) NOT NULL
  ,year INTEGER(5) NOT NULL
  ,month INTEGER(5) NOT NULL
  ,day INTEGER(5) NOT NULL
  ,PRIMARY KEY (formula_id) 
) DEFAULT CHARSET=utf8;