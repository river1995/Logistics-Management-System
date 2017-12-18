package com.lms.user.dao;

import java.util.List;

import com.lms.commom.domain.QueryEntity;
import com.lms.user.entities.UserEntity;

public interface UserDao {
	
	/**
	 * 用户登录
	 * @param username
	 * @param passwrd
	 * @return
	 */
	UserEntity login(String username ,String password);
	
	/**
	 * 添加新用户
	 * @param userEntity
	 * @return
	 */
	int addUser(UserEntity userEntity);
	
	
	/**
	 * 获取用户信息列表
	 * @param type
	 * @return
	 */
	List<UserEntity> userList(int type ,QueryEntity queryEntity);
	
	/**
	 * 计算用户总数，用于分页计算
	 * @param type
	 * @return
	 */
	public int countUser(int type);
	
	/**
	 * 检测用户名是否重复
	 * @param userName
	 * @return
	 */
	int checkUserName(String userName);
	
	int disableUser(int id);
	
	int getRemainNo(int userId);
	
	int addRemainNum(int userId ,int remainNum);
	
	int proxyCountUser(int userId);
	
	
	List<UserEntity> proxyUserList(QueryEntity queryEntity ,int userId);
}
