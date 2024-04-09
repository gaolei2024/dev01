package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.CategoryDao;
import com.itheima.dao.impl.CategoryDaoImpl;
import com.itheima.domain.Category;
import com.itheima.domain.PageBean;
import com.itheima.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> findCategoryList() {
		
		CategoryDao dao = new CategoryDaoImpl();
		List<Category> categoryList = null;
		try {
			categoryList = dao.findCategoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return categoryList;
	}

	@Override
	public PageBean getPageBean(int currentPage, int currentCount) {
		//封装分页PageBean
		CategoryDao dao = new CategoryDaoImpl();
		
		try {
			PageBean<Category> pageBean = new PageBean<>();
			//private int total;//总条数
			int total = dao.getTotal();
			pageBean.setTotal(total);
			//private List<T> rows;//当前页显示的数据
			int index = (currentPage-1)*currentCount;
			//sql:select * from category limit ?,?
			List<Category> rows = dao.findCategoryListByPage(index,currentCount);
			pageBean.setRows(rows);
			
			return pageBean;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addCategory(Category category) {
		CategoryDao dao = new CategoryDaoImpl();
		int result = 0;
		try {
			result = dao.addCategory(category);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result>0;
	}

	@Override
	public boolean delCategoryByCid(String cid) {
		CategoryDao dao = new CategoryDaoImpl();
		int result = 0;
		try {
			result = dao.delCategoryByCid(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result>0;
	}

	@Override
	public Category findCategoryByCid(String cid) {
		CategoryDao dao = new CategoryDaoImpl();
		Category category = null;
		try {
			category = dao.findCategoryByCid(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public boolean editCategory(Category category) {
		CategoryDao dao = new CategoryDaoImpl();
		int result = 0;
		try {
			result = dao.editCategory(category);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result>0;
	}

	
	
}
