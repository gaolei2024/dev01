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
		
		//Map列表作成
		Map<String,Object> map = new HashMap<>();
		
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			String tempPath = this.getServletContext().getRealPath("temp");
			factory.setRepository(new File(tempPath));
			factory.setSizeThreshold(1024*1024);
			//ファイルアップロード初期化
			ServletFileUpload upload = new ServletFileUpload(factory);
			//リクエスト作成
			List<FileItem> parseRequest = upload.parseRequest(request);
			//リクエスト内容繰り返し処理
			for(FileItem item:parseRequest){
				boolean formField = item.isFormField();
				if(formField){
					//ファイル名設定
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					map.put(name, value);
				}else{
					//ファイル名設定
					String filename = item.getName();
					InputStream in = item.getInputStream();
					String filePath = this.getServletContext().getRealPath("upload");
					OutputStream out = new FileOutputStream(filePath+"/"+filename);
					IOUtils.copy(in, out);
					in.close();
					out.close();
					item.delete();
					
					//pimage設定
					map.put("pimage", "upload/"+filename);//upload/1.jpg
					
				}
			}

			Product product = new Product();
			BeanUtils.populate(product, map);

			product.setPid(CommonsUtils.getUUID());

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			product.setPdate(format.format(new Date()));

			product.setPflag(0);

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