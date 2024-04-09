package com.itheima.service;

import java.util.List;

import com.itheima.domain.Category;
import com.itheima.domain.PageBean;

public interface CategoryService {

	List<Category> findCategoryList();

	PageBean getPageBean(int currentPage, int currentCount);

	boolean addCategory(Category category);

	boolean delCategoryByCid(String cid);

	Category findCategoryByCid(String cid);

	boolean editCategory(Category category);

}
