package com.bms.utils.entities;

import java.util.List;

public class PageEntity<T> {  
	private int total;//总记录数  
	private List<T> rows;//实体集合  
   
	public int getTotal() {  
		return total;  	
	}  
	public void setTotal(int total) {  
		this.total= total;  
	}  
	public List<T> getRows() {  
		return rows;  
	}  
	public void setRows(List<T> rows) {  
		this.rows= rows;  
	}  
}  