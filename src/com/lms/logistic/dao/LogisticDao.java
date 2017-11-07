package com.lms.logistic.dao;

import java.util.List;

import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.entities.LogisticStatusEntity;

public interface LogisticDao {
	
	/**
	 * 添加物流订单，返回订单主键
	 * @param logisticEntity
	 * @return
	 */
	int addLogistic(LogisticEntity logisticEntity);
	
	/**
	 * 添加物流订单状态
	 * @return
	 */
	int addLogisticStatus(List<LogisticStatusEntity> list ,int orderId);
	
	/**
	 * 删除物流信息，用于数据回滚
	 * @param orderId
	 * @return
	 */
	int deleteLogistic(int orderId);
}