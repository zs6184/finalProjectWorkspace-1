package tw.springbootfinal.Coupons.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.springbootfinal.Coupons.model.Coupons;
import tw.springbootfinal.Coupons.model.CouponsService;


@Controller
public class CouponsController {
	@Autowired
	CouponsService couponsService;
	
	//折扣碼首頁
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
	//新增折扣碼
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
	//刪除折扣碼
	@RequestMapping(path = "/back.deletecoupon", method = RequestMethod.POST)
	@ResponseBody
	public String deleteCoupons(@RequestParam("couponId") int id) {
		couponsService.deleteCoupon(id);
		return null;
	}

}
