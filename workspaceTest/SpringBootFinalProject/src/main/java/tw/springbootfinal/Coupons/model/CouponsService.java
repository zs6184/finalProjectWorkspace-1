package tw.springbootfinal.Coupons.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CouponsService {

	@Autowired
	CouponsRepository cRepository;

	// 查詢所有折扣碼:
	public List<Coupons> allCoupon() {
		return cRepository.findAll();
	}

	// 新增折扣碼
	public void savecoupons(Coupons coupons) {
		cRepository.save(coupons);
	}

	// 刪除折扣碼
	public void deleteCoupon(int id) {
		cRepository.deleteById(id);
	}

	// 檢查折扣碼
	public ArrayList<Object> checkCoupon(int couponCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		try {
			List<Coupons> coupon = cRepository.findByCouponCode(couponCode);
			list.add(coupon.get(0));
			// 判斷資料庫折扣碼是否存在及過期
			if (coupon.get(0) != null) {
				if (cheackDate(coupon.get(0).getCouponDue())) {
					System.out.println("日期正確");
					list.add(2);
				} else {
					System.out.println("日期過期");
					list.add(1);
				}
			} else {
				list.add(0);
			}

			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	// 比較日期工具方法//檢查優惠碼是否過期
	private boolean cheackDate(Date couponDue) {
		Date systemDate = new Date(System.currentTimeMillis());
		return couponDue.compareTo(systemDate) > 0;
	}

}
