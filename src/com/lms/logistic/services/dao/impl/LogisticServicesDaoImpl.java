package com.lms.logistic.services.dao.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.bms.commom.domain.QueryEntity;
import com.bms.utils.common.DateFormatUtil;
import com.bms.utils.common.GenerateLogisticInfoUtil;
import com.bms.utils.common.RandomUtil;
import com.bms.utils.common.RequestLogisticUtil;
import com.bms.utils.common.StringUtil;
import com.lms.logistic.dao.impl.LogisticDaoImpl;
import com.lms.logistic.entities.LogisticDetailEntity;
import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.entities.LogisticStatusEntity;
import com.lms.logistic.services.dao.LogisticServiceDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class LogisticServicesDaoImpl implements LogisticServiceDao {
	
	private LogisticDaoImpl logisticDao = new LogisticDaoImpl();
	private GenerateLogisticInfoUtil generateUtil = new GenerateLogisticInfoUtil();
	private RequestLogisticUtil requestUtil = new RequestLogisticUtil();
	private StringUtil stringUtil = new StringUtil();

	@Override
	public boolean addLogistic(LogisticDetailEntity detailEntity) {
		boolean flag = false;
		int orderId = logisticDao.addLogistic(detailEntity.getLogisticEntity());
		if (orderId > 0) {
			
			int rs = logisticDao.addLogisticStatus(detailEntity.getStatusEntity(), orderId);
			if (rs > 0) {
				flag = true;
			}else{
				logisticDao.deleteLogistic(orderId);
			}
		}
		return flag;
		
	}

	@Override
	public List<LogisticStatusEntity> generateLogsiticInfo(LogisticEntity logisticEntity) {
		List<LogisticStatusEntity> list = 	generateUtil.generateLogisticInfoUtil(logisticEntity);
		return list;
	}

	@Override
	public List<LogisticStatusEntity> statusList(int orderId) {
		List<LogisticStatusEntity> list = logisticDao.statusList(orderId);
		return list;
	}

	@Override
	public List<LogisticEntity> logisticList(int userId ,QueryEntity queryEntity) {
		List<LogisticEntity> list = logisticDao.logisticList(userId ,queryEntity);
		return list;
	}

	@Override
	public List<LogisticStatusEntity> customerLogisticList(String orderSeq) {
		List<LogisticStatusEntity> list = logisticDao.customerLogisticList(orderSeq);
		LogisticEntity  logisticEntity = logisticDao.getLogisticNo(orderSeq);
		String company = logisticEntity.getLogisticCompany();
		String logNo = logisticEntity.getLogisticNo();
		String finishTimeStr = logisticEntity.getCreateLogTime();
		if (!stringUtil.isNullString(logNo) && !stringUtil.isNullString(company) && !stringUtil.isNullString(finishTimeStr)) {
			long finishTime = (long)(Long.parseLong(finishTimeStr)-(1000*60*60*(RandomUtil.getRandomTime(4, 6))));					
			JSONObject object = requestUtil.requestLogisticInfo(logisticEntity.getLogisticNo(), logisticEntity.getLogisticCompany());
			if (object != null) {
				JSONArray jsonArray = object.getJSONArray("data");
				if (jsonArray != null && jsonArray.size()>0) {
					LogisticStatusEntity gatewayStatusEntity = new LogisticStatusEntity("Express completed customs clearance, has been handed over to Chinese courier company, the following data from the Chinese courier company",DateFormatUtil.changeLongTimeToString(finishTime));
					list.add(gatewayStatusEntity);
					for(int i = 0;i<jsonArray.size();i++){
						LogisticStatusEntity statusEntity = new LogisticStatusEntity();
						statusEntity.setAddress(jsonArray.getJSONObject(i).getString("context"));
						String timeResult = "";
						String[] times = jsonArray.getJSONObject(i).getString("time").split(":");
						timeResult = times[0]+":"+times[1].split(":")[0];
						statusEntity.setTime(timeResult);
						list.add(statusEntity);
					}
				}
			}
		}
		Collections.sort(list,new Comparator<LogisticStatusEntity>(){
			@Override
			public int compare(LogisticStatusEntity status1, LogisticStatusEntity status2) {
				// TODO Auto-generated method stub
				return status1.getTime().compareTo(status2.getTime());
			}
        });
		return list;
	}

	@Override
	public LogisticEntity logisticInfo(int orderId) {
		LogisticEntity logisticEntity = logisticDao.logisticInfo(orderId);
		return logisticEntity;
	}

	@Override
	public boolean addLogisticNo(String logisticNo, String logisticCompany, int orderId) {
		boolean flag = false;
		int rs = logisticDao.addLogisticNo(logisticNo, logisticCompany, orderId);
		if (rs > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public LogisticEntity logisticInfoById(String orderSeq) {
		LogisticEntity logisticEntity = logisticDao.logisticInfoById(orderSeq);
		return logisticEntity;
	}
	
	public static void main(String[] args) {
//		System.out.println(DateFormatUtil.changeTimeStampToUnixTime("2017-11-11 17:22"));
//		System.out.println(DateFormatUtil.changeTimeStampToUnixTime("2017-11-11 17:23"));
//		System.out.println(DateFormatUtil.changeTimeStampToUnixTime("2017-11-11 17:24"));
//		System.out.println(DateFormatUtil.changeTimeStampToUnixTime("2017-11-11 17:25"));
//		System.out.println(DateFormatUtil.changeTimeStampToUnixTime("2017-11-11 17:26"));
//		System.out.println(DateFormatUtil.changeTimeStampToUnixTime("2017-11-11 17:27"));
//		System.out.println(DateFormatUtil.changeTimeStampToUnixTime("2017-11-11 17:28"));
		System.out.println(DateFormatUtil.changeLongTimeToString(Long.parseLong("1510511700000")));
		//System.out.println(DateFormatUtil.changeTimeStampToUnixTime("2017-11-13 02:35"));
	}

	@Override
	public int getOrderCounts(int userId) {
		int  result = logisticDao.getOrderCounts(userId);
		return result;
	}


	

}
