package com.lms.logistic.services.dao.impl;

import java.util.List;

import com.bms.utils.common.GenerateLogisticInfoUtil;
import com.lms.logistic.dao.impl.LogisticDaoImpl;
import com.lms.logistic.entities.LogisticDetailEntity;
import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.entities.LogisticStatusEntity;
import com.lms.logistic.services.dao.LogisticServiceDao;


public class LogisticServicesDaoImpl implements LogisticServiceDao {
	
	private LogisticDaoImpl logisticDao = new LogisticDaoImpl();
	private GenerateLogisticInfoUtil generateUtil = new GenerateLogisticInfoUtil();

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
	public List<LogisticEntity> logisticList() {
		List<LogisticEntity> list = logisticDao.logisticList();
		return list;
	}

	@Override
	public List<LogisticStatusEntity> customerLogisticList(String orderSeq) {
		List<LogisticStatusEntity> list = logisticDao.customerLogisticList(orderSeq);
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


	

}
