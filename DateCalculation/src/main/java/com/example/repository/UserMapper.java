package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.MUser;

@Mapper
public interface UserMapper {

	/** ユーザー登録 */
	public void signup(MUser user);

	/** ログインユーザー取得 */
	public MUser getLoginUser(String userId);

}
