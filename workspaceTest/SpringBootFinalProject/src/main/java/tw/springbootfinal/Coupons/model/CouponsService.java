package tw.springbootfinal.Coupons.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CouponsService {
	
	@Autowired
	CouponsRepository cRepository;
	
	//查詢所有折扣碼:
	public List<Coupons> allCoupon() {
		return cRepository.findAll();
	}
	
	//新增折扣碼
	public void savecoupons(Coupons coupons) {
		cRepository.save(coupons);
	}
	//刪除折扣碼
	public void deleteCoupon(int id) {
		cRepository.deleteById(id);
	}
	
	
}
