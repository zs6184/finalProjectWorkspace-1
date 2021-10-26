package tw.finalspring.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
	
	public void deleteCoupon(int id) {
		couponsDAO.deleteCoupon(id);
	}
	
	public  ArrayList<Object> checkCoupon(int couponCode) {
		return couponsDAO.checkCoupon(couponCode);	
	}
	
	
}
