package com.lms.logistic.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bms.utils.common.DBConnector;
import com.bms.utils.common.DateFormatUtil;
import com.lms.logistic.dao.LogisticDao;
import com.lms.logistic.entities.LogisticDetailEntity;
import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.entities.LogisticStatusEntity;

import net.sf.json.JSONArray;

public class LogisticDaoImpl implements LogisticDao {

	@Override
	public int addLogistic(LogisticEntity logisticEntity) {
		int id = 0;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			String sql = "insert into `order`(order_seq,from_city,gateway_city,expire_time,logistic_company,created_at,from_country) values(?,?,?,?,?,?,?)";
			conn = DBConnector.getConnection();
			stat = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stat.setString(1, logisticEntity.getOrderSeq());
			stat.setString(2, logisticEntity.getFromCity());
			stat.setString(3, logisticEntity.getGatewayCity());
			stat.setString(4, logisticEntity.getExpireTime());
			stat.setString(5, logisticEntity.getLogisticCompany());
			stat.setLong(6, System.currentTimeMillis());
			stat.setString(7, logisticEntity.getFromCountry());
			System.out.println("LogisticDaoImpl.addLogistic():"+stat.toString());
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
		System.out.println("56:"+JSONArray.fromObject(list));
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
				stat.setLong(4, DateFormatUtil.changeTimeStampToUnixTime(statusEntity.getTime()));
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

	@Override
	public List<LogisticStatusEntity> statusList(int orderId) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stat = null;
		List<LogisticStatusEntity> list = new ArrayList<>();
		try {
			String sql = "select id,address,located_at from order_status where order_id=?";
			conn = DBConnector.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setInt(1, orderId);
			System.out.println("LogisticDaoImpl.statusList():" + stat.toString());
			rs = stat.executeQuery();
			while(rs.next()){
				LogisticStatusEntity logisticEntity = new LogisticStatusEntity();
				logisticEntity.setId(rs.getInt("id"));
				logisticEntity.setAddress(rs.getString("address"));
				logisticEntity.setTime(DateFormatUtil.changeLongTimeToString(rs.getLong("located_at")));
				list.add(logisticEntity);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(conn);
		}
		return list;
	}

	@Override
	public List<LogisticEntity> logisticList() {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stat = null;
		List<LogisticEntity> list = new ArrayList<>();
		try {
			String sql = "select id,order_seq,counts,created_at,finished_at,from_city,gateway_city,logistic_company,from_country  from `order` ";
			conn = DBConnector.getConnection();
			stat = conn.prepareStatement(sql);
			System.out.println("LogisticDaoImpl.logisticList():" + stat.toString());
			rs = stat.executeQuery();
			while(rs.next()){
				LogisticEntity logisticEntity = new LogisticEntity();
				logisticEntity.setId(rs.getInt("id"));
				logisticEntity.setFromCity(rs.getString("from_city"));
				logisticEntity.setFromCountry(rs.getString("from_country"));
				logisticEntity.setGatewayCity(rs.getString("gateway_city"));
				logisticEntity.setCounts(rs.getInt("counts"));
				logisticEntity.setLogisticCompany(rs.getString("logistic_company"));
				logisticEntity.setOrderSeq(rs.getString("order_seq"));
				logisticEntity.setCreateTime(DateFormatUtil.changeLongTimeToString(rs.getLong("created_at")));
				logisticEntity.setFinishTime(DateFormatUtil.changeDateToSimple(rs.getLong("finished_at")));
				list.add(logisticEntity);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(conn);
		}
		return list;
	}

	@Override
	public List<LogisticStatusEntity> customerLogisticList(String orderSeq) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stat = null;
		List<LogisticStatusEntity> list = new ArrayList<>();
		try {
			String sql = "select os.located_at,os.address from `order` join order_status os on `order`.id = os.order_id where `order`.order_seq = ? and os.located_at<?";
			conn = DBConnector.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setString(1, orderSeq);
			stat.setLong(2, System.currentTimeMillis());
			System.out.println("LogisticDaoImpl.customerLogisticList():" + stat.toString());
			rs = stat.executeQuery();
			while(rs.next()){
				LogisticStatusEntity statusEntity = new LogisticStatusEntity();
				statusEntity.setAddress(rs.getString("address"));
				statusEntity.setTime(DateFormatUtil.changeLongTimeToString(rs.getLong("located_at")));
				list.add(statusEntity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.closeConnection(conn);
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
	

}
