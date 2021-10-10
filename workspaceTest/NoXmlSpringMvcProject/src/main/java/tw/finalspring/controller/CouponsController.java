package tw.finalspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.finalspring.model.Coupons;
import tw.finalspring.service.CouponsService;

@Controller
public class CouponsController {

	@Autowired
	CouponsService couponsService;

	@RequestMapping(path = "/back.coupons")
	public String couponsPage(Model m) {
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
		//System.out.println("delete in");
		couponsService.deleteCoupon(id);
		//System.out.println(deleteCoupon);
		//return "redirect:/back.coupons";
		return null;
	}

}
