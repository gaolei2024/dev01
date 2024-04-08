package com.itheima.service;

import com.itheima.domain.PageBean;
import com.itheima.domain.Product;

public interface ProductService {

	PageBean getPageBean(int currentPage, int currentCount);

	boolean addProduct(Product product);

}
