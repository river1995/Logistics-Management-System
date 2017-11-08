package com.lms.logistic.entities;

public class LogisticStatusEntity {
	private int id;
	private String address;
	private String time	;
	
	
	public LogisticStatusEntity() {
		super();
	}
	public LogisticStatusEntity(String address, String time) {
		super();
		this.address = address;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
}
