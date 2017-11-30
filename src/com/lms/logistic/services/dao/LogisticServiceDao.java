package com.lms.logistic.services.dao;

import java.util.List;

import com.bms.commom.domain.QueryEntity;
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
	List<LogisticStatusEntity> statusList(int orderId);
	
	/**
	 * 获取订单列表
	 * @param logisticEntity
	 * @return
	 */
	List<LogisticEntity> logisticList(int userId ,QueryEntity queryEntity);
	
	/**
	 * 客户获取物流信息
	 * @param orderSeq
	 * @return
	 */
	List<LogisticStatusEntity> customerLogisticList(String orderSeq);
	
	/**
	 * 获取客户物流的详细信息
	 * @param orderId
	 * @return
	 */
	LogisticEntity logisticInfo(int orderId);
	
	boolean addLogisticNo(String logisticNo ,String logisticCompany ,int orderId);
	
	LogisticEntity logisticInfoById(String orderSeq);
	
	/**
	 * 获取所有订单列表总数
	 * @param userId
	 * @return
	 */
	int getOrderCounts(int userId);
	
	/**
	 * 通过快递100移动端api查询快递
	 * @param orderSeq
	 * @return
	 */
	public List<LogisticStatusEntity> queryByKD100Mobile(String orderSeq);
}
