package com.bms.user.services.impl;

import com.bms.user.dao.impl.UserDaoImpl;
import com.bms.user.entities.UserEntity;
import com.bms.user.services.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDaoImpl userDao = new UserDaoImpl();

	@Override
	public UserEntity login(String username, String password) {
		UserEntity userEntity = userDao.login(username, password);
		return userEntity;
	}

}
