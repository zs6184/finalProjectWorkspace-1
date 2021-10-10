package tw.finalspring.model;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Component
@Transactional
public class CouponsDAO {

	@Autowired
	private SessionFactory sessionfactory;

	// «O¦s
	public void saveCoupon(Coupons coupone) {
		Session session = sessionfactory.openSession();
		try {
			if (coupone != null) {
				session.save(coupone);
				System.out.println("done");
			} else {
				System.out.println("null");
			}
		} catch (Exception e) {
			System.out.println("fail");
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Coupons> allCoupon() {
		Session session = sessionfactory.openSession();
		try {
			Query<Coupons> createQuery = session.createQuery("from Coupons", Coupons.class);
			System.out.println("done!");

			return createQuery.list();
		} catch (Exception e) {
			System.out.println("fail!" + e.getMessage());

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return null;
	}
	//§R°£Àu´f½X
	public void deleteCoupon(int id) {
		Session session = sessionfactory.openSession();

		try {
//			 Query query = session.createQuery("delete from Coupons where couponId= :cid");
//			 query.setParameter("cid", id);
//			 int execute = query.executeUpdate();
//			 System.out.println(execute);
			Transaction txn = session.beginTransaction();
			Coupons coupons = session.get(Coupons.class, id);
			System.out.println(coupons.toString());
			session.delete(coupons);
			System.out.println("Delete Done!");
			txn.commit();
		} catch (Exception e) {
			System.out.println("Delete Fail!" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
