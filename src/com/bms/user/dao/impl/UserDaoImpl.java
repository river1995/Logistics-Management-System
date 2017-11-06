package com.bms.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bms.user.dao.UserDao;
import com.bms.user.entities.UserEntity;
import com.bms.utils.common.DBConnector;
import com.bms.utils.common.DateFormatUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public UserEntity login(String username, String password) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		UserEntity userEntity = new UserEntity();
		try {
			conn = DBConnector.getConnection();
			String sql = "select id,username,type from user where username=? and password=?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, username);
			stat.setString(2, password);
			System.out.println("UserDaoImpl.login():"+stat.toString());
			rs = stat.executeQuery();
			while(rs.next()){
				userEntity.setId(rs.getInt("id"));
				userEntity.setUsername(rs.getString("username"));
				userEntity.setType(rs.getInt("type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(conn);
		}
		return userEntity;
	}
	public static void main(String[] args) {
		System.out.println(DateFormatUtil.changeLongTimeToString(1361710691786l));
	}

}
