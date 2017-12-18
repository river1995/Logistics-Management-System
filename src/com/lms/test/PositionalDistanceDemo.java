package com.lms.test;
//package com.bms.test.demo;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.bms.incident.entities.IncidentEntity;
//import com.bms.incident.entities.ResponseDistanceEntity;
//import com.bms.user.entities.AddressesEntity;
//import com.bms.user.entities.SecurityEntity;
//import com.bms.utils.common.DBConnector;
//import com.mysql.jdbc.Security;
//
//import net.sf.json.JSONArray;
//
//public class PositionalDistanceDemo {
//	public List<ResponseDistanceEntity> getSecurityByDistance(IncidentEntity incident) {
//		List<ResponseDistanceEntity> list = null;
//		Connection conn = DBConnector.getConnection();
//		PreparedStatement stat = null;
//		try {
//			String sql = "SELECT *, (2*6378137*ASIN(SQRT(POW(SIN((? * PI() / 180.0 - ud.latitude * PI() / 180.0)/2),2)+COS(? * PI() / 180.0)*COS(ud.latitude * PI() / 180.0)*POW(SIN((? * PI() / 180.0 - LONGITUDE * PI() / 180.0)/2),2)))) AS distance FROM user u JOIN user_detail ud ON u.id = ud.user_id WHERE type = 1 ORDER BY distance ASC";
//			stat = conn.prepareStatement(sql);
//			stat.setString(1, incident.getAddress().getLatitude());
//			stat.setString(2, incident.getAddress().getLatitude());
//			stat.setString(3, incident.getAddress().getLongitude());
//
//			ResultSet rs = stat.executeQuery();
//			System.out.println(stat.toString());
//			list = new ArrayList<ResponseDistanceEntity>();
//			while (rs.next()) {
//				ResponseDistanceEntity distanceEntity = new ResponseDistanceEntity();
//				distanceEntity.setId(rs.getInt("id"));
//				distanceEntity.setDistance(rs.getDouble("distance"));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			DBConnector.closeConnection(conn);
//		}
//		return list;
//	}
//	
//	
//	public static void main(String[] args) {
//		PositionalDistanceDemo positionDemo = new PositionalDistanceDemo();
//		IncidentEntity incident = new IncidentEntity();
//		AddressesEntity addressesEntity = new AddressesEntity();
//		addressesEntity.setLongitude("118.159809");
//		addressesEntity.setLatitude("24.528956");;
//		incident.setAddress(addressesEntity);
//		List<ResponseDistanceEntity> list = positionDemo.getSecurityByDistance(incident);
//		JSONArray jsonArray = JSONArray.fromObject(list);
//		System.out.println(jsonArray);
//	}
//}
