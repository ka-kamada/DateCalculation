package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.User;

@Mapper
public interface UserMapper {

	/** ユーザー登録 */
	public void setUser(User user);

}
