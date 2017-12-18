package com.lms.user.services.impl;

import java.util.List;

import com.lms.commom.domain.QueryEntity;
import com.lms.user.dao.impl.UserDaoImpl;
import com.lms.user.entities.UserEntity;
import com.lms.user.services.UserService;

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

	@Override
	public boolean disableUser(int id) {
		boolean flag = false;
		int rs = userDao.disableUser(id);
		if (rs > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public int getRemainNo(int userId) {
		int remainNo = userDao.getRemainNo(userId);
		return remainNo;
	}

	@Override
	public boolean addRemainNum(int userId, int remainNum) {
		boolean flag = false;
		int addRs = userDao.addRemainNum(userId, remainNum);
		if (addRs > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public int proxyCountUser(int userId) {
		int rs = userDao.proxyCountUser(userId);
		return rs;
	}

	@Override
	public List<UserEntity> proxyUserList(QueryEntity queryEntity, int userId) {
		List<UserEntity> list = userDao.proxyUserList(queryEntity, userId);
		return list;
	}

}
