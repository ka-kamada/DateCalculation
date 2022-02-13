package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.MUser;
import com.example.repository.UserMapper;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	/** ユーザー登録 */
	@Override
	public void signup(MUser user) {

		user.setRole("ROLE_GENERAL"); // ロール

		// パスワード暗号化
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));

		mapper.signup(user);

	}

	/** ログインユーザー情報取得 */
	@Override
	public MUser getLoginUser(String userId) {
		return mapper.getLoginUser(userId);
	}

}
