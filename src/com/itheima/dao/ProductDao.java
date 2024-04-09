package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.itheima.domain.Product;

public interface ProductDao {

	int getCount() throws SQLException;

	List<Map<String, Object>> findProductListByPage(int index, int currentCount) throws SQLException;

	int addProduct(Product product) throws SQLException;

}
