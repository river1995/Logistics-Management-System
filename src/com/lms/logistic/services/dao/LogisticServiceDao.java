package com.lms.logistic.services.dao;

import java.util.List;

import com.lms.logistic.entities.LogisticDetailEntity;
import com.lms.logistic.entities.LogisticEntity;
import com.lms.logistic.entities.LogisticStatusEntity;

public interface LogisticServiceDao {
	
	/**
	 * 添加物流信息
	 * @param logisticEntity
	 * @return
	 */
	boolean addLogistic(LogisticDetailEntity detailEntity);
	
	/**
	 * 自动生成物流信息算法
	 * @param logisticEntity
	 * @return
	 */
	
	
	List<LogisticStatusEntity> generateLogsiticInfo(LogisticEntity logisticEntity);
	
	/**
	 * 获取物流信息列表
	 * @param logisticEntity
	 * @return
	 */
	List<LogisticDetailEntity> logisticList();
}
