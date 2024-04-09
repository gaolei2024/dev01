package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.dao.ProductDao;
import com.itheima.dao.impl.ProductDaoImpl;
import com.itheima.domain.Category;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public PageBean getPageBean(int currentPage, int currentCount) {
		
		ProductDao dao = new ProductDaoImpl();
		
		//PageBean実例化
		try {
			//total取得
			PageBean<Product> pageBean = new PageBean<>();
			int total = dao.getCount();
			pageBean.setTotal(total);
			//インデックス算出
			int index = (currentPage-1)*currentCount;
			List<Map<String,Object>> mapList = dao.findProductListByPage(index,currentCount);
			
			List<Product> rows = new ArrayList<>();
			
			if(mapList!=null){
				for(Map<String,Object> map:mapList){
					Product product = new Product();
					Category category = new Category();
					BeanUtils.populate(product, map);
					BeanUtils.populate(category, map);
					
					product.setCategory(category);
					
					rows.add(product);
				}
			}
			
			pageBean.setRows(rows);
			
			return pageBean;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean addProduct(Product product) {
		ProductDao dao = new ProductDaoImpl();
		int result = 0;
		try {
			result = dao.addProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result>0;
	}

}
