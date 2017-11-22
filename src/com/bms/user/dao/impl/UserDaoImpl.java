package com.bms.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bms.commom.domain.QueryEntity;
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
	@Override
	public int addUser(UserEntity userEntity) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rs = 0;
		try {
			conn = DBConnector.getConnection();
			String sql = "insert into `user`(username,password,type,created_at,phone,remain_num,proxy_id) values(?,?,?,?,?,?,?) ";
			stat = conn.prepareStatement(sql);
			stat.setString(1, userEntity.getUsername());
			stat.setString(2, userEntity.getPassword());
			stat.setInt(3, userEntity.getType());
			stat.setLong(4, System.currentTimeMillis());
			stat.setString(5, userEntity.getPhone());
			stat.setInt(6, userEntity.getRemainNum());
			stat.setString(7, userEntity.getProxy());
			System.out.println("UserDaoImpl.addUser():"+stat.toString());
			stat.executeUpdate();
			rs = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(conn);
		}
		return rs;
	}
	@Override
	public List<UserEntity> userList(int type ,QueryEntity queryEntity) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<UserEntity> list = new ArrayList<UserEntity>();
		try {
			conn = DBConnector.getConnection();
			String sql = "select id,username,password,type,created_at,total,remain_num,phone from `user` where type=? limit ?,?";
			stat = conn.prepareStatement(sql);
			stat.setInt(1, type);
			stat.setInt(2, queryEntity.getOffset());
			stat.setInt(3, queryEntity.getLimit());
			System.out.println("UserDaoImpl.userList():"+stat.toString());
			rs = stat.executeQuery();
			while(rs.next()){
				UserEntity userEntity = new UserEntity();
				userEntity.setId(rs.getInt("id"));
				userEntity.setUsername(rs.getString("username"));
				userEntity.setType(rs.getInt("type"));
				userEntity.setCreateTime(DateFormatUtil.changeLongTimeToString(rs.getLong("created_at")));
				userEntity.setTotal(rs.getInt("total"));
				userEntity.setRemainNum(rs.getInt("remain_num"));
				userEntity.setPhone(rs.getString("phone"));
				list.add(userEntity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(conn);
		}
		return list;
	}
	
	public int countUser(int type) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnector.getConnection();
			String sql = "select count(id) as number from `user` where type=? ";
			stat = conn.prepareStatement(sql);
			stat.setInt(1, type);
			System.out.println("UserDaoImpl.countUser():"+stat.toString());
			rs = stat.executeQuery();
			while(rs.next()){
				count = rs.getInt("number");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(conn);
		}
		return count;
	}
	@Override
	public int checkUserName(String userName) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		int id = 0;
		try {
			conn = DBConnector.getConnection();
			String sql = "select id from `user` where username=?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, userName);
			System.out.println("UserDaoImpl.checkUserName():"+stat.toString());
			rs = stat.executeQuery();
			while(rs.next()){
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(conn);
		}
		return id;
	}

}
