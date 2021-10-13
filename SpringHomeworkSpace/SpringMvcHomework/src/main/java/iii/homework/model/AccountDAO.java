package iii.homework.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository 
public class AccountDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean checkLogin(Account users) {
//		Session session = sessionFactory.getCurrentSession();
		Session session = sessionFactory.openSession();
		
		String hqlstr = "from Account where username=:user and userpwd=:pwd";
		Query<Account> query = session.createQuery(hqlstr,Account.class);
		query.setParameter("user", users.getUserName());
		query.setParameter("pwd", users.getUserPwd());
		
		Account account = query.uniqueResult();
		
		session.close();
		
		if(account!=null) {
			return true;
		}
		
		return false;
	}
	
}
