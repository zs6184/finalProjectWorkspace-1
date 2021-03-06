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
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

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

	// ????????????
	@RequestMapping(path = "/chackout")
	public String checkout(HttpSession session, Model m, orderBean order, HttpServletRequest request)
			throws UnsupportedEncodingException {
		CustomerBean user = cService.getLoginCustomerBean(session);
		System.out.println(user.getCusRealname());
		CustomerBean resultUser = cService.getLoginCustomerBean(session);
		System.out.println("????????????" + resultUser.getCusUsername());
		// ????????????
		// ProductsBean selecybyId = sService.selecyProductbyId(id);
		// ??????cookies
		Cookie[] cookies = request.getCookies();
		String cookieStr = null;
		for (Cookie c : cookies) {
			System.out.println(c.getName());
			if (c.getName().equals("cart" + resultUser.getCusUsername())) {
				// if(c.getName().equals("cart123")) {
				System.out.println(c.getValue());
				cookieStr = URLDecoder.decode(c.getValue(), "utf-8");
			}
		}
		// JSON???????????????????????????
		if (cookieStr != null) {
			Set<ProductsBean> list = new HashSet<ProductsBean>();
			System.out.println("??????cookie====" + cookieStr);
			List<ProductsBean> cartitem = JSON.parseArray(cookieStr, ProductsBean.class);
			for (ProductsBean o : cartitem) {
				ProductsBean target = sService.selecyProductbyId(o.getId());
				target.setnum(o.getnum());
				list.add(target);
			}
			for (ProductsBean p : list) {
				System.out.println("????????????ID:" + p.getId() + "??????????????????:" + p.getName() + "????????????" + p.getnum());
			}
			m.addAttribute("catitem", list);
		}
		m.addAttribute("user", user);

		return "checkout";
	}

	// ????????????
	@RequestMapping(path = "/saveorder", method = RequestMethod.POST)
	public String saveOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model m,
			orderBean order, @RequestParam("pickuptime1") String picktime,
			@RequestParam(value = "couponsId", defaultValue = "0") int couponId)
			throws ParseException, UnsupportedEncodingException {
		// ???????????????
		CustomerBean user = cService.getLoginCustomerBean(session);
		order.setCustomer(user);
		// ??????????????????
		order.setOrderstatus("?????????");
		// ??????????????????//0?????????1?????????
		order.setPaystatus(0);
		// ????????????
		if (order.getNote() == null || order.getNote().equals("")) {
			order.setNote("????????????");
		}

		// ??????????????????
		Set<orderDetailsBean> orderDetails = order.getOrderDetails();
		Integer totalprice = 0;
		Cookie[] cookies = request.getCookies();
		String cookieStr = null;
		for (Cookie c : cookies) {
			if (c.getName().equals("cart" + user.getCusUsername())) {
				// if(c.getName().equals("cart123")) {
				cookieStr = URLDecoder.decode(c.getValue(), "utf-8");
				break;
			}
		}
		// JSON???????????????????????????
		if (cookieStr != null) {
			System.out.println("??????cookie====" + cookieStr);
			List<ProductsBean> cartitem = JSON.parseArray(cookieStr, ProductsBean.class);
			for (ProductsBean o : cartitem) {
				orderDetailsBean detail = new orderDetailsBean();
				ProductsBean target = sService.selecyProductbyId(o.getId());
				detail.setProduct(target);
				detail.setOrder(order);
				detail.setNum(o.getnum());
				detail.setUnitprice(target.getPrice());
				detail.setSubtotal(target.getPrice() * o.getnum());
				totalprice += detail.getSubtotal();
				orderDetails.add(detail);
			}
		}
		// ??????????????? ??? ?????????
		if (couponId != 0) {
			Coupons coupon = couponsService.findByid(couponId);
			totalprice = totalprice - coupon.getCouponDiscount();
			order.setTotal(totalprice);
			order.setCoupons(coupon);
		} else {
			order.setTotal(totalprice);
		}
		// ?????????????????????
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date parse = formatter.parse(picktime.replace("T", " "));
		// ??????????????????
		Date now = new Date(System.currentTimeMillis());
		order.setOrdertime(new Timestamp(now.getTime()));
		// System.out.println("????????????:"+formatter.format(now));
		// ??????????????????
		order.setPickuptime(new Timestamp(parse.getTime()));
		System.out.println("??????????????????:" + formatter.format(order.getPickuptime()));
		System.out.println("??????????????????:" + formatter.format(order.getOrdertime()));
		oService.saveOrder(order);
		m.addAttribute("user", user);
		return "redirect:/order/orderdetail";
	}

	// ????????????
	@RequestMapping("/orderdetail")
	public String checkoutdetail(HttpSession session, Model m, HttpServletRequest request, HttpServletResponse response)
			throws TemplateNotFoundException, MalformedTemplateNameException, freemarker.core.ParseException,
			IOException, TemplateException {
		CustomerBean user = cService.getLoginCustomerBean(session);
		// ????????????????????????
		orderBean order = oService.latestOrder(user);
		// ????????????
		// ??????cookies
		Cookie[] cookies = request.getCookies();
		String cookieStr = null;
		for (Cookie c : cookies) {
			System.out.println(c.getName());
			if (c.getName().equals("cart" + user.getCusUsername())) {
				// if(c.getName().equals("cart123")) {
				System.out.println(c.getValue());
				cookieStr = URLDecoder.decode(c.getValue(), "utf-8");
				// ???????????????
				c.setPath("/");
				c.setMaxAge(0);
				response.addCookie(c);
				break;
			}
		}
		// JSON???????????????????????????
		if (cookieStr != null) {
			Set<ProductsBean> list = new HashSet<ProductsBean>();
			System.out.println("??????cookie====" + cookieStr);
			List<ProductsBean> cartitem = JSON.parseArray(cookieStr, ProductsBean.class);
			for (ProductsBean o : cartitem) {
				ProductsBean target = sService.selecyProductbyId(o.getId());
				target.setnum(o.getnum());
				list.add(target);
			}
			for (ProductsBean p : list) {
				System.out.println("????????????ID:" + p.getId() + "??????????????????:" + p.getName() + "????????????" + p.getnum());
			}
			m.addAttribute("catitem", list);
		}
		// ???????????????
		if (order.getCoupons() != null) {
			m.addAttribute("coupon", order.getCoupons());
		}
		// ??????mail
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", user.getCusUsername());
		model.put("orderId", order.getId());
		model.put("price", order.getTotal());
		String templateNmae = "order.html";
		String head = "???????????????";
		 mService.sendMail(request, user,model,templateNmae,head);
		// ???order
		m.addAttribute("order", order);

		return "checkoutdetail";
	}

	// ??????????????????
	@RequestMapping("/back.ordermanage.controller")
	public String BackOrdermanage(Model m) {
		// ???????????????
		List<orderBean> resoultOrder = oService.findByOrderstatus("?????????");
		// ????????????
		Collections.reverse(resoultOrder);
		if (!resoultOrder.isEmpty()) {
			for (orderBean o : resoultOrder) {
				System.out.println("??????id:" + o.getId());
				System.out.println("?????????:" + o.getTotal());
				System.out.println("??????:" + o.getNote());
			}
			m.addAttribute("newOrder", resoultOrder);
		} else {
			System.out.println("????????????");
		}

		return "ordermanage";
	}

	// ??????????????????
	@RequestMapping(value = "/getbackorders.controller")
	@ResponseBody
	public ArrayList<Object> getneworder(@RequestParam("status") String status) throws IOException, JSONException {
		System.out.println("=============");
		ArrayList<orderBean> resoultOrder = (ArrayList<orderBean>) oService.findByOrderstatus(status);
		ArrayList<Object> List = new ArrayList<Object>();
		System.out.println("toString:" + resoultOrder);
//			PrintWriter out = response.getWriter();
		if (!resoultOrder.isEmpty()) {
			for (orderBean o : resoultOrder) {
				o.getCustomer().setOrderBean(null);
				Set<orderDetailsBean> orderDetails = o.getOrderDetails();
				for (orderDetailsBean ob : orderDetails) {
					ob.getProduct().setOrderDetails(null);
				}
				List.add(JSON.toJSONString(o));
			}
			return List;
		} else {
			System.out.println("????????????");
		}
		return null;
	}

	// ??????????????????
	@RequestMapping(value = "/changestate", method = RequestMethod.POST)
	@ResponseBody
	public String changestate(@RequestParam("orderid") int id, @RequestParam("status") String status) {
		// ????????????
		orderBean order = oService.selectbyid(id);
		if (status.equals("?????????") || status.equals("??????")) {
			order.setPaystatus(1);
		}
		order.setOrderstatus(status);
		// ????????????
		oService.saveOrder(order);
		return "????????????????????????";
	}

	// ??????????????????
	@RequestMapping("/back.searchorder")
	@ResponseBody
	public ArrayList<Object> searchorder(@RequestParam("str") String str) {
		System.out.println(str);
		ArrayList<orderBean> resoultOrder = (ArrayList<orderBean>) oService.backsearchOrder(str);
		ArrayList<Object> List = new ArrayList<Object>();
		System.out.println("toString:" + resoultOrder);
		if (!resoultOrder.isEmpty()) {
			for (orderBean o : resoultOrder) {
				o.getCustomer().setOrderBean(null);
				Set<orderDetailsBean> orderDetails = o.getOrderDetails();
				for (orderDetailsBean ob : orderDetails) {
					ob.getProduct().setOrderDetails(null);
				}
				List.add(JSON.toJSONString(o));
				System.out.println(o.getNote());
			}
			return List;
		} else {
			System.out.println("????????????");
		}
		return null;
	}

	// ??????????????????
	@RequestMapping("/memberorders")
	public String memberorders() {
		return "orderdetail";
	}

	// ?????????????????????????????????
	@RequestMapping("/findorderbyusername")
	@ResponseBody
	public ArrayList<Object> findorderbyusername(HttpSession session) {
		CustomerBean userBean = cService.getLoginCustomerBean(session);
		ArrayList<Object> List = new ArrayList<Object>();
		//Set<orderBean> resoultOrder = userBean.getOrderBean();
		ArrayList<orderBean> resoultOrder = (ArrayList<orderBean>)oService.findByOrdercus(userBean.getCusId());
		System.out.println(resoultOrder);
		if (!resoultOrder.isEmpty()) {
			for (orderBean o : resoultOrder) {
				o.getCustomer().setOrderBean(null);
				Set<orderDetailsBean> orderDetails = o.getOrderDetails();
				for (orderDetailsBean ob : orderDetails) {
					ob.getProduct().setOrderDetails(null);
				}
				List.add(JSON.toJSONString(o));
				System.out.println(o.getNote());
			}
			return List;
		} else {
			System.out.println("????????????");
		}
		return null;
	}

	// ??????id??????????????????
	@RequestMapping(path = "/findorderbyid")
	@ResponseBody
	public ArrayList<Object> findorderbyid(@RequestParam("productid") int id) {
		orderBean selectbyid = oService.selectbyid(id);
		ArrayList<Object> List = new ArrayList<Object>();
		selectbyid.getCustomer().setOrderBean(null);
		Set<orderDetailsBean> orderDetails = selectbyid.getOrderDetails();
		for (orderDetailsBean o : orderDetails) {
			o.getProduct().setOrderDetails(null);
		}
		List.add(JSON.toJSONString(selectbyid));
		return List;
	}

	// ????????????????????????
	@RequestMapping("/searchcusorder")
	@ResponseBody
	public ArrayList<Object> searchcusorder(HttpSession session, @RequestParam("str") String str) {
		CustomerBean userBean = cService.getLoginCustomerBean(session);
		System.out.println(userBean.getCusId() + "::" + str);
		ArrayList<orderBean> resoultOrder = (ArrayList<orderBean>) oService.backsearchOrder(str);
		ArrayList<Object> List = new ArrayList<Object>();
		System.out.println("toString:" + resoultOrder);
		if (!resoultOrder.isEmpty()) {
			for (orderBean o : resoultOrder) {
				if (o.getCustomer().getCusId() == userBean.getCusId()) {
					o.getCustomer().setOrderBean(null);
					Set<orderDetailsBean> orderDetails = o.getOrderDetails();
					for (orderDetailsBean ob : orderDetails) {
						ob.getProduct().setOrderDetails(null);
					}
					List.add(JSON.toJSONString(o));
					System.out.println(o.getNote());
				}
			}
			return List;
		} else {
			System.out.println("????????????");
		}
		return null;
	}

	// ???????????????
	// ??????
	@RequestMapping("/checkout")
	@ResponseBody
	public String saveOrder2(HttpSession session) {
		// ??????
		CustomerBean resultUser = cService.getLoginCustomerBean(session);

		// ????????????
		orderBean order = new orderBean();
		// ????????????
		ProductsBean pro1 = sService.selecyProductbyId(1);
		ProductsBean pro2 = sService.selecyProductbyId(2);
		orderDetailsBean orderDetails1 = new orderDetailsBean();
		orderDetailsBean orderDetails2 = new orderDetailsBean();

		// ???????????????????????????????????????(1)
		orderDetails1.setOrder(order);
		orderDetails1.setProduct(pro1);
		orderDetails1.setNum(600);
		// ???????????????????????????????????????(2)
//		orderDetails2.setOrder(order);
//		orderDetails2.setProduct(pro2);
//		orderDetails2.setNum(200);
		// ????????????????????????
		Set<orderDetailsBean> orderDetails = order.getOrderDetails();
		orderDetails.add(orderDetails1);
		// orderDetails.add(orderDetails2);
		// ??????????????????
		order.setNote("????????????");
		order.setCustomer(resultUser);
		// ????????????
		oService.saveOrder(order);
		// ??????????????????
		System.out.println(
				"??????:" + order.getNote() + "??????ID:" + order.getId() + "??????:" + order.getCustomer().getCusUsername());
		return "ok:" + resultUser.getCusUsername();
	}

	// ????????????????????????
	@RequestMapping("/customeorder")
	@ResponseBody
	public String selectCustomerOrder(HttpSession session) {
		CustomerBean resultUser = cService.getLoginCustomerBean(session);
		Set<orderBean> orderBean = resultUser.getOrderBean();
		for (orderBean o : orderBean) {
			System.out.println("????????????:" + o.getId() + "??????:" + o.getCustomer().getCusUsername() + "??????id:"
					+ o.getCustomer().getCusId());
			Set<orderDetailsBean> cusorderDeatils = o.getOrderDetails();
			for (orderDetailsBean cd : cusorderDeatils) {
				ProductsBean product = cd.getProduct();
				System.out.println("??????ID:" + product.getId() + "????????????:" + product.getName() + "??????????????????" + cd.getNum());
			}
		}
		System.out.println("===???????????????????????????===");
		ProductsBean pro1 = sService.selecyProductbyId(1);
		int total = 0;
		Set<orderDetailsBean> orderDetails = pro1.getOrderDetails();
		for (orderDetailsBean o : orderDetails) {
			total += o.getNum();
		}
		System.out.println("??????????????????:" + total);
		return "ok";
	}

}
