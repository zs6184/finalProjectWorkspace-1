package tw.finalspring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import tw.finalspring.model.Coupons;
import tw.finalspring.service.CouponsService;
import tw.finalspring.service.MailService;

@Controller
public class CouponsController {

	@Autowired
	CouponsService couponsService;
	
	@Autowired
	MailService mailService;
	


	@RequestMapping(path = "/back.coupons")
	public String couponsPage(Model m) {
		System.out.println("進入");
		Coupons coupons = new Coupons();
		List<Coupons> Coupon = couponsService.allCoupon();
		m.addAttribute("coupon", coupons);
		m.addAttribute("allCoupons", Coupon);
		return "coupons";
		// return "index";
	}

	@RequestMapping(path = "/back.addcoupon", method = RequestMethod.POST)
	public String addCoupons(@ModelAttribute("coupon") Coupons coupon, Model m) {
		Coupons coupons = new Coupons();
		coupons.setCouponName(coupon.getCouponName());
		coupons.setCouponCode(coupon.getCouponCode());
		coupons.setCouponDiscount(coupon.getCouponDiscount());
		coupons.setCouponDue(coupon.getCouponDue());
		couponsService.savecoupons(coupons);
		// m.addAttribute("coupon",coupons);
		return "redirect:/back.coupons";
	}

	@RequestMapping(path = "/back.deletecoupon", method = RequestMethod.POST)
	@ResponseBody
	public String deleteCoupons(@RequestParam("couponId") int id) {
		couponsService.deleteCoupon(id);
		return null;
	}

	@RequestMapping(path = "/back.checkcoupon", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<Object> checkcoupon(@RequestParam("ccode") int id) {
		ArrayList<Object> checkCoupon = couponsService.checkCoupon(id);
		return checkCoupon;
	}

//	public String addToCart(@RequestParam("id") String id, @RequestParam("num") int num, RedirectAttributes ra) {
//		ra.addAttribute("輸入key", "輸入值");
//		ra.addFlashAttribute("放入OBJECT可以在頁面Session取出,但是只能取出一次");
//		return "redirect:/http://127.0.0.1:8080/NoXmlSpringMvcProject/xxxx";
//	}
	
	@RequestMapping(path = "/back.mailTest")
	@ResponseBody
	public String mailTest(HttpServletRequest request) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		if(mailService.sendMail(request,null))return "ok";
		return "fail";
	}
	
	

}
