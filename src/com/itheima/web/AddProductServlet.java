package com.itheima.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;
import com.itheima.utils.CommonsUtils;

public class AddProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//收集循环中表单数据的
		Map<String,Object> map = new HashMap<>();
		
		try {
			//文件上传的模板操作
			//1、创建工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			String tempPath = this.getServletContext().getRealPath("temp");
			factory.setRepository(new File(tempPath));
			factory.setSizeThreshold(1024*1024);
			//2、创建核心对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			//3、解析request 获得多个文件项对象FileItem
			List<FileItem> parseRequest = upload.parseRequest(request);
			//4、遍历判断是普通表单项还是文件上传项
			for(FileItem item:parseRequest){
				boolean formField = item.isFormField();
				if(formField){
					//普通表单 获得name和value  封装到Product实体
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					map.put(name, value);
				}else{
					//文件上传项
					String filename = item.getName();
					InputStream in = item.getInputStream();
					String filePath = this.getServletContext().getRealPath("upload");
					OutputStream out = new FileOutputStream(filePath+"/"+filename);
					IOUtils.copy(in, out);
					in.close();
					out.close();
					item.delete();
					
					//向map中添加pimage的值
					map.put("pimage", "upload/"+filename);//upload/1.jpg
					
				}
			}
			
			
			//将map中的数据 映射封装到Product对象内部
			Product product = new Product();
			BeanUtils.populate(product, map);
			
			//封装4个数据
			//封装pid
			product.setPid(CommonsUtils.getUUID());
			//封装pdate
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			product.setPdate(format.format(new Date()));
			//封装pflag
			product.setPflag(0);
			//封装category
			Category category = new Category();
			category.setCid(map.get("cid").toString());
			product.setCategory(category);
			
			ProductService service = new ProductServiceImpl();
			boolean isAddSuccess = service.addProduct(product);
			
			response.getWriter().write(isAddSuccess+"");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}