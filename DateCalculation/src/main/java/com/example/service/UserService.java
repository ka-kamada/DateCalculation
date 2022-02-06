package com.example.service;

import com.example.model.MUser;

public interface UserService {

	/** ユーザー登録 */
	public void signup(MUser user);

	/** ログインユーザー情報取得 */
	public MUser getLoginUser(String userId);

}
