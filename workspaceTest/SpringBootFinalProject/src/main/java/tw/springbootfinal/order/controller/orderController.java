package tw.springbootfinal.order.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.http.HttpRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import tw.springbootfinal.Coupons.model.Coupons;
import tw.springbootfinal.Coupons.model.CouponsService;
import tw.springbootfinal.mail.MailService;
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
	
	@Autowired
	CouponsService couponsService;
	
	@Autowired
	MailService mService;
	//訂單畫面
	@RequestMapping(path = "/chackout")
	public String checkout(HttpSession session, Model m, orderBean order,HttpServletRequest request) throws UnsupportedEncodingException {
		CustomerBean user = cService.getLoginCustomerBean(session);
		System.out.println(user.getCusRealname());
		CustomerBean resultUser = cService.getLoginCustomerBean(session);
		System.out.println("用戶名稱"+resultUser.getCusUsername());
		// 取得商品
		//ProductsBean selecybyId = sService.selecyProductbyId(id);
		// 取出cookies
		Cookie[] cookies = request.getCookies();
		String cookieStr = null;
		for(Cookie c:cookies) {
			System.out.println(c.getName());
			if(c.getName().equals("cart"+resultUser.getCusUsername())) {
			//if(c.getName().equals("cart123")) {
				System.out.println(c.getValue());
				cookieStr = URLDecoder.decode(c.getValue(), "utf-8");
			}
		}
		//JSON字串解析成商品物件
		if(cookieStr != null) {
			Set<ProductsBean> list = new HashSet<ProductsBean>();
			System.out.println("我是cookie====" + cookieStr);
			List<ProductsBean> cartitem = JSON.parseArray(cookieStr, ProductsBean.class);
			for (ProductsBean o : cartitem) {
				ProductsBean target = sService.selecyProductbyId(o.getId());
				target.setnum(o.getnum());
				list.add(target);
			}
			for (ProductsBean p : list) {
				System.out.println("我是商品ID:"+p.getId()+"我是商品名稱:"+p.getName()+"商品數量"+p.getnum());
			}
			m.addAttribute("catitem",list);
		}
		m.addAttribute("user", user);
		
		return "checkout";
	}
	// 保存訂單
	@RequestMapping(path = "/saveorder", method = RequestMethod.POST)
	public String saveOrder(HttpServletRequest request,HttpServletResponse response,HttpSession session, Model m, orderBean order,
																		@RequestParam("pickuptime1") String picktime, 
																	   @RequestParam(value = "couponsId",defaultValue = "0") int couponId)throws ParseException, UnsupportedEncodingException {
		//存入使用者
		CustomerBean user = cService.getLoginCustomerBean(session);
		order.setCustomer(user);
		//設定訂單狀態
		order.setOrderstatus("新訂單");
		//設定付款狀態//0未付款1已付款
		order.setPaystatus(0);
		//設定備註
		if(order.getNote() == null ||order.getNote().equals("")) {
			order.setNote("備註空白");
		}

		//加入訂單明細
		Set<orderDetailsBean> orderDetails = order.getOrderDetails();
		Integer totalprice = 0;
		Cookie[] cookies = request.getCookies();
		String cookieStr = null;
		for(Cookie c:cookies) {
			if(c.getName().equals("cart"+user.getCusUsername())) {
			//if(c.getName().equals("cart123")) {
				cookieStr = URLDecoder.decode(c.getValue(), "utf-8");
				break;
			}
		}
		//JSON字串解析成商品物件
		if(cookieStr != null) {
			System.out.println("我是cookie====" + cookieStr);
			List<ProductsBean> cartitem = JSON.parseArray(cookieStr, ProductsBean.class);
			for (ProductsBean o : cartitem) {
				orderDetailsBean detail = new orderDetailsBean();
				ProductsBean target = sService.selecyProductbyId(o.getId());
				detail.setProduct(target);
				detail.setOrder(order);
				detail.setNum(o.getnum());
				detail.setUnitprice(target.getPrice());
				detail.setSubtotal(target.getPrice() * o.getnum());
				totalprice += target.getPrice() * o.getnum();
				orderDetails.add(detail);
			}
		}
		//設定優惠碼 及 總金額
		if(couponId !=0) {
			Coupons coupon = couponsService.findByid(couponId);
			totalprice = totalprice - coupon.getCouponDiscount();
			order.setTotal(totalprice);
			order.setCoupons(coupon);
		}else {
			order.setTotal(totalprice);
		}
		//格式化取餐時間
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date parse = formatter.parse(picktime.replace("T", " "));
		//存入現在時間
		Date now = new Date(System.currentTimeMillis());
		order.setOrdertime(new Timestamp(now.getTime()));
		//System.out.println("下單時間:"+formatter.format(now));
		//存入取餐時間
		order.setPickuptime(new Timestamp(parse.getTime()));
		System.out.println("取餐訂單時間:"+formatter.format(order.getPickuptime()));
		System.out.println("下單訂單時間:"+formatter.format(order.getOrdertime()));
		oService.saveOrder(order);
		m.addAttribute("user", user);
		return "redirect:/order/orderdetail";
	}
	//訂單明細
	@RequestMapping("/orderdetail")
	public String checkoutdetail(HttpSession session,Model m,HttpServletRequest request,HttpServletResponse response) throws TemplateNotFoundException, MalformedTemplateNameException, freemarker.core.ParseException, IOException, TemplateException {
		CustomerBean user = cService.getLoginCustomerBean(session);
		//取得最近一筆訂單
		orderBean order = oService.latestOrder(user);
		//商品明細
		// 取出cookies
		Cookie[] cookies = request.getCookies();
		String cookieStr = null;
		for(Cookie c:cookies) {
			System.out.println(c.getName());
			if(c.getName().equals("cart"+user.getCusUsername())) {
			//if(c.getName().equals("cart123")) {
				System.out.println(c.getValue());
				cookieStr = URLDecoder.decode(c.getValue(), "utf-8");
				//清空購物車
				c.setPath("/");
				c.setMaxAge(0);
				response.addCookie(c);
				break;
			}
		}
		//JSON字串解析成商品物件
		if(cookieStr != null) {
			Set<ProductsBean> list = new HashSet<ProductsBean>();
			System.out.println("我是cookie====" + cookieStr);
			List<ProductsBean> cartitem = JSON.parseArray(cookieStr, ProductsBean.class);
			for (ProductsBean o : cartitem) {
				ProductsBean target = sService.selecyProductbyId(o.getId());
				target.setnum(o.getnum());
				list.add(target);
			}
			for (ProductsBean p : list) {
				System.out.println("我是商品ID:"+p.getId()+"我是商品名稱:"+p.getName()+"商品數量"+p.getnum());
			}
			m.addAttribute("catitem",list);
		}
		//判斷折扣碼
		if(order.getCoupons() != null) {
			m.addAttribute("coupon",order.getCoupons());
		}
		//發送mail
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", user.getCusUsername());
		model.put("orderId", order.getId());
		model.put("price", order.getTotal());
		String templateNmae="order.html";
		String head ="訂單受理中";
		mService.sendMail(request, user,model,templateNmae,head);
		//存order
		m.addAttribute("order",order);
		
		return"checkoutdetail";
	}
	
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
		// orderDetails.add(orderDetails2);
		// 填入訂單信息
		order.setNote("備註測試");
		order.setCustomer(resultUser);
		// 儲存訂單
		oService.saveOrder(order);
		// 列印訂單信息
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
			System.out.println("訂單編號:" + o.getId() + "用戶:" + o.getCustomer().getCusUsername() + "用戶id:"
					+ o.getCustomer().getCusId());
			Set<orderDetailsBean> cusorderDeatils = o.getOrderDetails();
			for (orderDetailsBean cd : cusorderDeatils) {
				ProductsBean product = cd.getProduct();
				System.out.println("商品ID:" + product.getId() + "商品名稱:" + product.getName() + "購買商品數量" + cd.getNum());
			}
		}
		System.out.println("===查看商品被買走幾個===");
		ProductsBean pro1 = sService.selecyProductbyId(1);
		int total = 0;
		Set<orderDetailsBean> orderDetails = pro1.getOrderDetails();
		for (orderDetailsBean o : orderDetails) {
			total += o.getNum();
		}
		System.out.println("總共被買數量:" + total);
		return "ok";
	}

}
