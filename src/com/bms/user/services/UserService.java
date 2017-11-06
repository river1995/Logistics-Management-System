package com.bms.user.services;

import com.bms.user.entities.UserEntity;

public interface UserService {
	/**
	 * 登陆
	 * @param username
	 * @param password
	 * @return
	 */
	UserEntity login(String username,String password);
}
