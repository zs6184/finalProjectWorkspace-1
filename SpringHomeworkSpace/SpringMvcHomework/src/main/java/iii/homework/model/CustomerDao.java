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
	@Autowired
    private SessionFactory sessionFactory;
	
	// 登入時搜尋會員帳號
	public Query<CustomerBean> selectUsernameLogin(CustomerBean username) {
		String hql = "from CustomerBean as c where c.cusUsername = :username";
		Session session = sessionFactory.getCurrentSession();
		Query<CustomerBean> query = session.createQuery(hql,
				CustomerBean.class).setParameter("username",
				username.getCusUsername());
		return query;
	}

	// 註冊時搜尋會員帳號
	public List<CustomerBean> selectUsername(CustomerBean username) {
		String hql = "from CustomerBean as c where c.cusUsername = :username";
		Session session = sessionFactory.getCurrentSession();
		Query<CustomerBean> query = session.createQuery(hql,
				CustomerBean.class).setParameter("username",
				username.getCusUsername());
		List<CustomerBean> list = query.getResultList();
		return list;
	}

	//新增
	public CustomerBean insert(CustomerBean cBean) {
		Session session = sessionFactory.getCurrentSession();
		CustomerBean cusBean = session.get(CustomerBean.class, cBean.getCusId());
		if (cusBean == null) {
			session.save(cBean);
			return cBean;
		}
		return null;
	}
	
	//使用Id取得CustomerBean物件
	public CustomerBean selectById(int cusId) {
		Session session = sessionFactory.getCurrentSession();
		CustomerBean temp = session.get(CustomerBean.class, cusId);
		
		return temp;
	}
	
	//修改個人資料
	public CustomerBean updateOne(int cusId,CustomerBean temp) {
		Session session = sessionFactory.getCurrentSession();
		CustomerBean check = session.get(CustomerBean.class, cusId);
		if(check!=null) {
			check.setBean(temp);
			return check;
		}
		return null;
	}
	
	//刪除個人資料
	public void deleteOne(int cusId) {
		Session session = sessionFactory.getCurrentSession();
		CustomerBean temp = session.get(CustomerBean.class, cusId);
		if(temp != null) {
			session.delete(temp);
			System.out.println("刪除完畢");
		}
	}
}
