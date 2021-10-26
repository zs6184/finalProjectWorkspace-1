package tw.springbootfinal.Coupons.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponsRepository extends JpaRepository<Coupons,Integer> {
	
	//利用折扣碼查找
	public List<Coupons> findByCouponCode(int couponCode);
}
