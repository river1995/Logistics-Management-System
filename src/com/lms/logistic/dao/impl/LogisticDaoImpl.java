package com.lms.logistic.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bms.utils.common.DBConnector;
import com.bms.utils.common.DateFormatUtil;
import com.lms.logistic.dao.LogisticDao;
import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.entities.LogisticStatusEntity;

public class LogisticDaoImpl implements LogisticDao {

	@Override
	public int addLogistic(LogisticEntity logisticEntity) {
		int id = 0;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			String sql = "insert into order(user_id,order_seq,from_city,gateway_city,expire_time,logistic_company,created_at) values(?,?,?,?,?,?,?)";
			conn = DBConnector.getConnection();
			stat = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stat.setInt(1, logisticEntity.getUserId());
			stat.setString(2, logisticEntity.getOrderSeq());
			stat.setString(3, logisticEntity.getFromCity());
			stat.setString(4, logisticEntity.getGatewayCity());
			stat.setString(5, logisticEntity.getExpireTime());
			stat.setString(6, logisticEntity.getLogisticCompany());
			stat.setLong(7, System.currentTimeMillis());
			stat.executeUpdate();
			rs = stat.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(conn);
		}
		return id;

	}

	@Override
	public int addLogisticStatus(List<LogisticStatusEntity> list, int orderId) {
		int rs = 0;
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			String sql = "insert into order_status(order_id,address,created_at,located_at) values(?,?,?,?)";
			conn = DBConnector.getConnection();
			conn.setAutoCommit(false);
			stat = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				LogisticStatusEntity statusEntity = list.get(i);
				stat.setInt(1, orderId);
				stat.setString(2, statusEntity.getAddress());
				stat.setLong(3, System.currentTimeMillis());
				stat.setLong(4, DateFormatUtil.changeTimeStampToUnixTime(statusEntity.getLocatedTime()));
				stat.addBatch();
			}
			System.out.println("LogisticDaoImpl.addLogisticStatus():" + stat.toString());
			stat.executeBatch();
			conn.commit();
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
	public int deleteLogistic(int orderId) {
		int rs = 0;
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			String sql = "delete from order where id=?";
			conn = DBConnector.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setInt(1, orderId);
			System.out.println("LogisticDaoImpl.addLogisticStatus():" + stat.toString());
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

}
