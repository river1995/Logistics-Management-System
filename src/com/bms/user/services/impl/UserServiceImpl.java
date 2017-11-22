package com.bms.user.services.impl;

import java.util.List;

import com.bms.commom.domain.QueryEntity;
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

	@Override
	public boolean addUser(UserEntity userEntity) {
		boolean flag = false;
		int rs = userDao.addUser(userEntity);
		if (rs > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<UserEntity> userList(int type ,QueryEntity queryEntity) {
		List<UserEntity> list = userDao.userList(type ,queryEntity);
		return list;
	}

	@Override
	public int countUser(int type) {
		int number = userDao.countUser(type);
		return number;
	}

	@Override
	public boolean checkUserName(String username) {
		boolean flag = false;
		int id = userDao.checkUserName(username);
		if ( !(id > 0)) {
			flag = true;
		}
		return flag;
	}

}
