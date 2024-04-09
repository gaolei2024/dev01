package com.itheima.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;

public class ProductServlet extends BaseServlet {

	public void findAllProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//分页需要的数据 total rows
		int currentPage = Integer.parseInt(request.getParameter("page"));
		int currentCount = Integer.parseInt(request.getParameter("rows"));
		
		ProductService service = new ProductServiceImpl();
		
		PageBean<Product> pageBean = service.getPageBean(currentPage,currentCount);
		
		
		Gson gson = new Gson();
		
		String json = gson.toJson(pageBean);
		
		response.getWriter().write(json);
		
		
		
	}

}
