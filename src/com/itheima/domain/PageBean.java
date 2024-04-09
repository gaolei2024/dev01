package com.itheima.domain;

import java.util.List;

public class PageBean<T> {

	//easyui的分页page
	private int total;//总条数
	private List<T> rows;//当前页显示的数据
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
	
	
	
}
