package tw.springbootfinal.order.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.springbootfinal.order.model.orderBean;
import tw.springbootfinal.order.model.orderDetailsBean;
import tw.springbootfinal.order.model.orderServic;
import tw.springbootfinal.shoppingcart.model.ProductsBean;
import tw.springbootfinal.shoppingcart.model.ShopCartService;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
@RequestMapping(path = "/order")
public class orderController {
	@Autowired
	orderServic oService;

	@Autowired
	ShopCartService sService;

	@Autowired
	CustomerService cService;
	
	@RequestMapping(path = "/chackout")
	public String checkout(HttpSession session,Model m,orderBean order) {
		CustomerBean user = cService.getLoginCustomerBean(session);
		System.out.println(user.getCusRealname());
		m.addAttribute("user",user);
		return "checkout";
	}
	
	@RequestMapping(path = "/saveorder",method = RequestMethod.POST)
	public String saveOrder(HttpSession session,Model m,orderBean order) {
		CustomerBean user = cService.getLoginCustomerBean(session);
		m.addAttribute("user",user);
		System.out.println(order.getNote());
		return "checkout";
	}

	// 保存訂單
	@RequestMapping("/checkout")
	@ResponseBody
	public String saveOrder2(HttpSession session) {
		// 用戶
		CustomerBean resultUser = cService.getLoginCustomerBean(session);

		// 建立訂單
		orderBean order = new orderBean();
		// 購買產品
		ProductsBean pro1 = sService.selecyProductbyId(1);
		ProductsBean pro2 = sService.selecyProductbyId(2);
		orderDetailsBean orderDetails1 = new orderDetailsBean();
		orderDetailsBean orderDetails2 = new orderDetailsBean();
		
		// 訂單明細存入產品及關連訂單(1)
		orderDetails1.setOrder(order);
		orderDetails1.setProduct(pro1);
		orderDetails1.setNum(600);
		// 訂單明細存入產品及關連訂單(2)
//		orderDetails2.setOrder(order);
//		orderDetails2.setProduct(pro2);
//		orderDetails2.setNum(200);
		// 訂單儲存訂單明細
		Set<orderDetailsBean> orderDetails = order.getOrderDetails();
		orderDetails.add(orderDetails1);
		//orderDetails.add(orderDetails2);
		//填入訂單信息
		order.setNote("備註測試");
		order.setCustomer(resultUser);
		//儲存訂單
		oService.saveOrder(order);
		//列印訂單信息
		System.out.println(
				"備註:" + order.getNote() + "訂單ID:" + order.getId() + "用戶:" + order.getCustomer().getCusUsername());
		return "ok:" + resultUser.getCusUsername();
	}

	// 查詢用戶所有訂單
	@RequestMapping("/customeorder")
	@ResponseBody
	public String selectCustomerOrder(HttpSession session) {
		CustomerBean resultUser = cService.getLoginCustomerBean(session);
		Set<orderBean> orderBean = resultUser.getOrderBean();
		for (orderBean o : orderBean) {
			System.out.println("訂單編號:"+o.getId()+"用戶:"+o.getCustomer().getCusUsername()+"用戶id:"+o.getCustomer().getCusId());
			Set<orderDetailsBean> cusorderDeatils = o.getOrderDetails();
			for(orderDetailsBean cd:cusorderDeatils) {
				ProductsBean product = cd.getProduct();
				System.out.println("商品ID:"+product.getId()+"商品名稱:"+product.getName()+"購買商品數量"+cd.getNum());
			}
		}
		System.out.println("===查看商品被買走幾個===");
		ProductsBean pro1 = sService.selecyProductbyId(1);
		int total = 0;
		Set<orderDetailsBean> orderDetails = pro1.getOrderDetails();
		for(orderDetailsBean o:orderDetails) {
			total +=o.getNum();
		}
		System.out.println("總共被買數量:"+total);
		return "ok";
	}

}
