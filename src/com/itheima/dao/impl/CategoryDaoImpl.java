package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.dao.CategoryDao;
import com.itheima.domain.Category;
import com.itheima.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> findCategoryList() throws SQLException {
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		List<Category> categoryList = runner.query(sql, new BeanListHandler<Category>(Category.class));
		
		return categoryList;
	}

	@Override
	public int getTotal() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from category";
		Long query = (Long) runner.query(sql, new ScalarHandler());
		return query.intValue();
	}

	@Override
	public List<Category> findCategoryListByPage(int index, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category limit ?,?";
		List<Category> rows = runner.query(sql, new BeanListHandler<Category>(Category.class), index,currentCount);
		return rows;
	}

	@Override
	public int addCategory(Category category) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values(?,?)";
		int update = runner.update(sql,category.getCid(),category.getCname());
		
		return update;
	}

	@Override
	public int delCategoryByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from category where cid=?";
		int update = runner.update(sql,cid);
		
		return update;
	}

	@Override
	public Category findCategoryByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category where cid=?";
		Category category = runner.query(sql, new BeanHandler<Category>(Category.class), cid);
		return category;
	}

	@Override
	public int editCategory(Category category) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update category set cname=? where cid=?";
		int result = runner.update(sql, category.getCname(),category.getCid());
		return result;
	}

}
