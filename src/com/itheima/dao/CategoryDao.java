package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.Category;

public interface CategoryDao {

	List<Category> findCategoryList() throws SQLException;

	int getTotal() throws SQLException;

	List<Category> findCategoryListByPage(int index, int currentCount) throws SQLException;

	int addCategory(Category category) throws SQLException;

	int delCategoryByCid(String cid) throws SQLException;

	Category findCategoryByCid(String cid) throws SQLException;

	int editCategory(Category category) throws SQLException;

}
