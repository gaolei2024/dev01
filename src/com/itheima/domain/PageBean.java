package com.itheima.domain;

import java.util.List;

public class PageBean<T> {

	//全件数・総金額
	private int total;//������
	private List<T> rows;//��ǰҳ��ʾ������
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
