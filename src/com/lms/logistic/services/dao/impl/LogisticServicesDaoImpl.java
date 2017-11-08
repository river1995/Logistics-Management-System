package com.lms.logistic.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.lms.logistic.dao.impl.LogisticDaoImpl;
import com.lms.logistic.entities.LogisticDetailEntity;
import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.entities.LogisticStatusEntity;
import com.lms.logistic.services.dao.LogisticServiceDao;

public class LogisticServicesDaoImpl implements LogisticServiceDao {
	
	private LogisticDaoImpl logisticDao = new LogisticDaoImpl();

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
		List<LogisticStatusEntity> list = new ArrayList<>();		
		return list;
	}

}
