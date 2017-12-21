package com.lms.user.services;

import java.util.List;

import com.lms.commom.domain.QueryEntity;
import com.lms.user.entities.UserEntity;

public interface UserService {
	/**
	 * 登陆
	 * @param username
	 * @param password
	 * @return
	 */
	UserEntity login(String username,String password);
	
	/**
	 * 添加管理员账号
	 * @param userEntity
	 * @return
	 */
	boolean addUser(UserEntity userEntity);
	
	
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
	int countUser(int type);
	
	/**
	 * 检测用户名是否重复,重复返回false，不重复返回true
	 * @param username
	 * @return
	 */
	boolean checkUserName(String username);
	
	boolean disableUser(int id);
	
	int getRemainNo(int userId);
	
	boolean addRemainNum(int userId, int remainNum);
	
	int proxyCountUser(int userId);
	
	public List<UserEntity> proxyUserList(QueryEntity queryEntity ,int userId);
	
}