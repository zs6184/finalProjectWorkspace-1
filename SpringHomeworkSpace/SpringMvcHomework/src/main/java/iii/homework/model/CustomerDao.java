package iii.homework.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional
public class CustomerDao {
//	 implements CustomerDaoInterface
	@Autowired
    private SessionFactory sessionFactory;

//	@Override
	public CustomerBean select(int cId) {
		Session session = sessionFactory.openSession();
		CustomerBean cusBean = session.get(CustomerBean.class, cId);
		session.close();
		return cusBean;
	}

//	@Override
	public List<CustomerBean> selectAll() {
		Session session = sessionFactory.openSession();
		Query<CustomerBean> query = session.createQuery("from CustomerBean", CustomerBean.class);
		session.close();
		return query.list();
	}
	
	// 搜尋會員帳號
//	@Override
	public Query<CustomerBean> selectUsernameLogin(CustomerBean username) {
		String hql = "from CustomerBean as c where c.cusUsername = :username";
		Session session = sessionFactory.openSession();
		Query<CustomerBean> query = session.createQuery(hql, CustomerBean.class).setParameter("username",
				username.getCusUsername());
//		session.close();
		return query;
	}
	

	// 搜尋會員帳號
//	@Override
	public List<CustomerBean> selectUsername(CustomerBean username) {
		String hql = "from CustomerBean as c where c.cusUsername = :username";
		Session session = sessionFactory.openSession();
		Query<CustomerBean> query = session.createQuery(hql, CustomerBean.class).setParameter("username",
				username.getCusUsername());
		List<CustomerBean> list = query.getResultList();
		session.close();
		return list;
	}

	// 會員註冊時搜尋電話號碼
//	@Override
	public List<CustomerBean> selectPhone(CustomerBean phoneNumber) {
		String hql = "from CustomerBean as c where c.phoneNumber = :phoneNumber";
		Session session = sessionFactory.openSession();
		Query<CustomerBean> query = session.createQuery(hql, CustomerBean.class).setParameter("phoneNumber",
				phoneNumber.getPhoneNumber());
		List<CustomerBean> list = query.getResultList();
		session.close();
		return list;
	}

	// 會員註冊時搜尋Email
//	@Override
	public List<CustomerBean> selectEmail(CustomerBean email) {
		String hql = "from CustomerBean as c where c.email = :email";
		Session session = sessionFactory.openSession();
		Query<CustomerBean> query = session.createQuery(hql, CustomerBean.class).setParameter("email",
				email.getEmail());
		List<CustomerBean> list = query.getResultList();
		session.close();
		return list;
	}

//	@Override
	public CustomerBean insert(CustomerBean cBean) {
		Session session = sessionFactory.openSession();
		CustomerBean cusBean = session.get(CustomerBean.class, cBean.getCusId());
		if (cusBean == null) {
			session.save(cBean);
			session.close();
			return cBean;
		}
		session.close();
		return null;
	}

//	@Override
	public CustomerBean updateOne(int cId, CustomerBean cBean) {
		Session session = sessionFactory.openSession();
		CustomerBean cusBean = session.get(CustomerBean.class, cId);
		if (cusBean != null) {
			// 透過Bean取得資料更新到資料庫
			cusBean.setCusPassword(cBean.getCusPassword());
			cusBean.setAka(cBean.getAka());
			cusBean.setCusRealname(cBean.getCusRealname());
			cusBean.setGender(cBean.getGender());
			cusBean.setPhoneNumber(cBean.getPhoneNumber());
			cusBean.setEmail(cBean.getEmail());
			cusBean.setBirthdate(cBean.getBirthdate());
			cusBean.setAddress(cBean.getAddress());
			cusBean.setImage(cBean.getImage());
			cusBean.setNote(cBean.getNote());
			cusBean.setNoShow(cBean.getNoShow());
			session.close();
		}
		session.close();
		return cusBean;
	}

//	@Override
	public boolean deletOne(int cId) {
		Session session = sessionFactory.openSession();
		CustomerBean cusBean = session.get(CustomerBean.class, cId);
		if (cusBean != null) {
			session.delete(cusBean);
			session.close();
			return true;
		} else {
			session.close();
			return false;
		}
	}

}
