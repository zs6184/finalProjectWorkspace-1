package tw.finalspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.finalspring.model.Coupons;
import tw.finalspring.service.CouponsService;

@Controller
public class CouponsController {
	
	@Autowired
	CouponsService couponsService;
	
	@RequestMapping(path = "/back.coupons")
	public String couponspage (Model m) {
		Coupons coupons = new Coupons();
		List<Coupons> Coupon = couponsService.allCoupon();
		m.addAttribute("coupon",coupons);
		m.addAttribute("allCoupons",Coupon);
		return "coupons";
		//return "index";
	}
	
	@RequestMapping(path = "/back.addcoupon",method = RequestMethod.POST)
	public String addcoupons (@ModelAttribute("coupon")Coupons coupon,Model m) {
		Coupons coupons = new Coupons();
		coupons.setCouponName(coupon.getCouponName());
		coupons.setCouponCode(coupon.getCouponCode());
		coupons.setCouponDiscount(coupon.getCouponDiscount());
		coupons.setCouponDue(coupon.getCouponDue());
		couponsService.savecoupons(coupons);
		//m.addAttribute("coupon",coupons);
		 return "redirect:/back.coupons";
	}

}
