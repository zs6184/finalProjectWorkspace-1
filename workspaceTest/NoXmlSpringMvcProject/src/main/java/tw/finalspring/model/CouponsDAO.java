package tw.finalspring.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CouponsDAO {

	@Autowired
	private SessionFactory sessionfactory;

	// �s�W�u�f�X
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

	// �d�ߩҦ��u�f�X�A���u�f�X�D�����
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

	// �R���u�f�X
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
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// �u�f�X�ˬd//0�d�L1�L��2���T
	public  ArrayList<Object> checkCoupon(int couponCode) {
		Session session = sessionfactory.openSession();
		 ArrayList<Object> list = new  ArrayList<Object>();
		try {
			Transaction txn = session.beginTransaction();
			Query<Coupons> query = session.createQuery("from Coupons where couponCode =:code", Coupons.class);
			query.setParameter("code", couponCode);
			Coupons result = (Coupons) query.uniqueResult();
			txn.commit();
			list.add(result);
			//�P�_��Ʈw�馩�X�O�_�s�b�ιL��
			if (result != null) {
				if (cheackDate(result.getCouponDue())) {
					System.out.println("������T");
					list.add(2) ;
				} else {
					System.out.println("����L��");
					list.add(1) ;
				}
			}else {
				list.add(0);
			}
			
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}
	//�������u���k//�ˬd�u�f�X�O�_�L��
	private boolean cheackDate(Date couponDue) {
		Date systemDate = new Date(System.currentTimeMillis());
		return couponDue.compareTo(systemDate) > 0;
	}

}
