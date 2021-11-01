package tw.springbootfinal.products.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import tw.springbootfinal.products.model.ProductService;
import tw.springbootfinal.products.model.Products;

@Controller
@RequestMapping("/product")
public class FrontProductsController {
	@Autowired
	private ProductService prodService;
//----------------------------------------------

	// 查詢所有已上架產品並依照類別分類回傳
	@GetMapping("/findallproduct")
	public String findAllProduct(Model m) throws UnsupportedEncodingException {
		List<Products> allProd = prodService.findAllOnShelve("已上架");
		List<Products> arrRice = new ArrayList<>();
		List<Products> arrNoodle = new ArrayList<>();
		List<Products> arrBeverage = new ArrayList<>();
		List<Products> arrDessert = new ArrayList<>();
		Map<Integer,String> baseStr = new HashMap<>();

		for (Products aProd : allProd) {
			byte[] base64 = Base64.encodeBase64(aProd.getPic()); //轉成base64 byte陣列
			String base64Str = new String (base64,"UTF-8"); //轉成UTF-8編碼字串
			baseStr.put(aProd.getProductID(), base64Str);//把字串放到MAP中使用PrtId作為KEY值
			
			switch (aProd.getCategory()) {
			case "飯":
				arrRice.add(aProd);
				break;
			case "麵":
				arrNoodle.add(aProd);
				break;
			case "飲料":
				arrBeverage.add(aProd);
				break;
			case "甜點":
				arrDessert.add(aProd);
				break;
			}
		}
		m.addAttribute("baseStr",baseStr);
		m.addAttribute("arrRice",arrRice);
		m.addAttribute("arrNoodle",arrNoodle);
		m.addAttribute("arrBeverage",arrBeverage);
		m.addAttribute("arrDessert",arrDessert);		
		return "ProductIntro";
	}

	
	//使用ID查詢特定產品資料
	@GetMapping("/selectbyid")
	public void selectById(@RequestParam("id") Integer id,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		Products theProd  = prodService.selectById(id);
		//資料轉JSON物件
		String prodStr = (JSON.toJSONString(theProd, SerializerFeature.WriteMapNullValue));
		JSONObject prodData = JSONObject.parseObject(prodStr);
		
		PrintWriter out = response.getWriter();
		out.print(prodData);
		out.close();
		System.out.println("輸出完成");
	}
	


}
