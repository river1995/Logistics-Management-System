package com.lms.logistic.dao;

import java.util.List;

import com.lms.logistic.entities.LogisticDetailEntity;
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
	
	/**
	 * 获取物流信息列表
	 * @return
	 */
	List<LogisticStatusEntity> statusList(int orderId);
	
	/**
	 * 获取订单列表
	 * @return
	 */
	List<LogisticEntity> logisticList(int userId);
	
	/**
	 * 客户获取物流信息
	 * @param orderSeq
	 * @return
	 */
	List<LogisticStatusEntity> customerLogisticList(String orderSeq);
	
	/**
	 * 获取客户的详细物流信息
	 * @param orderId
	 * @return
	 */
	LogisticEntity logisticInfo(int orderId);
	
	int addLogisticNo(String logisticNo, String logisticCompany, int orderId);
	
	LogisticEntity logisticInfoById(String orderSeq);
	
	/**
	 * 获取用户的国内快递单号和快递公司信息
	 * @param orderSeq
	 * @return
	 */
	LogisticEntity getLogisticNo(String orderSeq);
}
