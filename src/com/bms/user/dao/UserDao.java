package com.bms.user.dao;

import com.bms.user.entities.UserEntity;

public interface UserDao {
	
	/**
	 * 用户登录
	 * @param username
	 * @param passwrd
	 * @return
	 */
	UserEntity login(String username ,String password);
	
}
