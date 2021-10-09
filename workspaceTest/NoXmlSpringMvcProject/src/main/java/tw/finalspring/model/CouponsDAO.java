package tw.finalspring.model;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public class CouponsDAO {
	
	@Autowired
	private SessionFactory sessionfactory;
	
	//«O¦s
	public void saveCoupon(Coupons coupone) {
		Session session = sessionfactory.openSession();
		try {
			if(coupone != null) {
				session.save(coupone);
				System.out.println("done");
			}else {
				System.out.println("null");
			}
		}catch (Exception e) {
			System.out.println("fail");
		}

	}

	public List<Coupons> allCoupon() {
		Session session = sessionfactory.openSession();
		try {
			Query<Coupons> createQuery = session.createQuery("from Coupons",Coupons.class);
			System.out.println("done!");
			return createQuery.list();
		}catch (Exception e) {
			System.out.println("fail!");
		}
		return null;
		
	}
}
