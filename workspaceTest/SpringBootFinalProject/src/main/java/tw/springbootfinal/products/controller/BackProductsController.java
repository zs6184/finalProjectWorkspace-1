package tw.springbootfinal.products.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import tw.springbootfinal.products.model.ProductService;
import tw.springbootfinal.products.model.Products;
import tw.springbootfinal.users.model.CustomerService;
import tw.springbootfinal.users.model.EmployeeService;

@Controller
@RequestMapping("/Backstage/product")
public class BackProductsController {

	@Autowired
	private ProductService prodService;
	@Autowired
	private CustomerService cService;
	@Autowired
	private EmployeeService empService;

	
//-----------------------------------------------------------------	
	
	//抓出所有產品的資料
	@GetMapping("/findAll")
	public String findAllProd(@SessionAttribute("username")String username,HttpServletRequest request,Model m) {
		List<Products> arrRes = prodService.findAll();
		m.addAttribute("arrRes",arrRes);
		//會員圖片
		String imageName = empService.selectImage(username, request);
		m.addAttribute("imageName",imageName);
		return "BackProducts";
	}
	
	
	//使用ID查詢一筆產品資料
	@GetMapping("/selectbyid")
	public void selectById(@RequestParam int id,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		Products temp = prodService.selectById(id);
		String prodStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue));
		JSONObject prodData = JSONObject.parseObject(prodStr);

		PrintWriter out = response.getWriter();
		out.print(prodData);
		out.close();
		System.out.println("輸出完成");
	}
	
	
	//新增一筆產品
	@PostMapping("/insertone")
	public String insertOne(Products temp,@RequestParam("mypic")MultipartFile pic) throws IOException {
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 寫出所有null後將所有空白轉null
		Products transfer = JSON.parseObject(jsonStr,Products.class);
		
		prodService.insertOne(transfer, pic);
		
		return "redirect:/Backstage/product/findAll";
	}
	
	//用ID刪除單筆資料
	@GetMapping("/deletebyid")
	public String deleteById(@RequestParam int id) {
		prodService.deleteById(id);
		
		return "redirect:/Backstage/product/findAll";
	}
	
	
	//修改單筆資料
	@PostMapping("/updateone")
	public String updateOne(Products temp,@RequestParam("mypic") MultipartFile pic) throws IOException {
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 將所有空白轉為null
		Products transfer = JSON.parseObject(jsonStr,Products.class);
		
		prodService.updateOne(transfer, pic);
		System.out.println("修改完成");
		return "redirect:/Backstage/product/findAll";
	}
		
}
