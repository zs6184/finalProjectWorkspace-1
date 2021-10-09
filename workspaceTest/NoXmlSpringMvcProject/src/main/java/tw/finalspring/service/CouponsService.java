package tw.finalspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.finalspring.model.Coupons;
import tw.finalspring.model.CouponsDAO;

@Service
@Transactional
public class CouponsService {
	@Autowired
	private CouponsDAO couponsDAO;
	
	public void savecoupons(Coupons coupons) {
		couponsDAO.saveCoupon(coupons);
	}
	
	public List<Coupons> allCoupon() {
		return couponsDAO.allCoupon();
	}

}
