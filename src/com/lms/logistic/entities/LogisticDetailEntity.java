package com.lms.logistic.entities;

import java.util.List;

public class LogisticDetailEntity {
	private LogisticEntity logisticEntity;
	private List<LogisticStatusEntity> statusEntity;
	public LogisticEntity getLogisticEntity() {
		return logisticEntity;
	}
	public void setLogisticEntity(LogisticEntity logisticEntity) {
		this.logisticEntity = logisticEntity;
	}
	public List<LogisticStatusEntity> getStatusEntity() {
		return statusEntity;
	}
	public void setStatusEntity(List<LogisticStatusEntity> statusEntity) {
		this.statusEntity = statusEntity;
	}
	
	
}
