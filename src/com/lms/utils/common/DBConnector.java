package com.lms.utils.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnector {
	private static final Logger logger = LoggerFactory.getLogger(DBConnector.class);

	static {
		try {
			Class.forName(ConstantsUtil.dbdriver);
		} catch (Exception e) {
			logger.error("从Property文件中获取值", e);
			throw new ExceptionInInitializerError(e);
		}
	}

	// get connection
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(ConstantsUtil.dburl, ConstantsUtil.dbusername, ConstantsUtil.dbpassword);
			System.out.println(ConstantsUtil.dburl+"\n"+ConstantsUtil.dbusername+"\n"+ConstantsUtil.dbpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("连接数据库", e);
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		try {

			conn.close();

		} catch (SQLException e) {
			logger.error("关闭数据库", e);

		}
	}

	// release connection
	public static void release(ResultSet rs, Statement st, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("关闭ResultSet", e);
			}
			rs = null;
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("关闭Statement", e);
			}
			st = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("关闭Connection", e);
			}
			conn = null;
		}
	}
}
