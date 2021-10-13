package tw.finalspring.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional
public class EmployeeDao {
	@Autowired
    private SessionFactory sessionFactory;

	public EmployeeBean select(int eId) {
		Session session = sessionFactory.getCurrentSession();
		EmployeeBean empBean = session.get(EmployeeBean.class, eId);
		return empBean;
	}

	public List<EmployeeBean> selectAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<EmployeeBean> query = session.createQuery("from EmployeeBean", EmployeeBean.class);
		return query.list();
	}
	
	public String selectMaxUsername() {
		//搜尋username最大值
		String hql = "from EmployeeBean as e where e.empUsername = (select max(eBD.empUsername) from EmployeeBean as eBD)";
		Session session = sessionFactory.getCurrentSession();
		Query<EmployeeBean> query = session.createQuery(hql,EmployeeBean.class);
		EmployeeBean rs = query.uniqueResult();
		return rs.getEmpUsername();
	}
	
	
	// 搜尋員工帳號
	public Query<EmployeeBean> selectUsernameLogin(EmployeeBean username) {
		String hql = "from CustomerBean as c where c.cusUsername = :username";
		Session session = sessionFactory.getCurrentSession();
		Query<EmployeeBean> query = session.createQuery(hql, EmployeeBean.class).setParameter("username",
				username.getEmpUsername());
		return query;
	}
	

	// 搜尋員工帳號
	public List<EmployeeBean> selectUsername(EmployeeBean username) {
		String hql = "from EmployeeBean as c where c.empUsername = :username";
		Session session = sessionFactory.getCurrentSession();
		Query<EmployeeBean> query = session.createQuery(hql, EmployeeBean.class).setParameter("username",
				username.getEmpUsername());
		List<EmployeeBean> list = query.getResultList();
		return list;
	}

	// 員工註冊時搜尋電話號碼
	public List<EmployeeBean> selectPhone(EmployeeBean phoneNumber) {
		String hql = "from EmployeeBean as e where e.phoneNumber = :phoneNumber";
		Session session = sessionFactory.getCurrentSession();
		Query<EmployeeBean> query = session.createQuery(hql, EmployeeBean.class).setParameter("phoneNumber",
				phoneNumber.getPhoneNumber());
		List<EmployeeBean> list = query.getResultList();
		return list;
	}


	public EmployeeBean insert(EmployeeBean eBean) {
		Session session = sessionFactory.getCurrentSession();
		EmployeeBean empBean = session.get(EmployeeBean.class, eBean.getEmpId());
		if (empBean == null) {
			session.save(eBean);
			return eBean;
		}
		return null;
	}

	public EmployeeBean updateOne(int eId, EmployeeBean eBean) {
		Session session = sessionFactory.getCurrentSession();
		EmployeeBean empBean = session.get(EmployeeBean.class, eId);
		if (empBean != null) {
			// 透過Bean取得資料更新到資料庫
			empBean.setEmpRealname(eBean.getEmpRealname());
			empBean.setHiredate(eBean.getHiredate());
			empBean.setTitle(eBean.getTitle());
			empBean.setGender(eBean.getGender());
			empBean.setPhoneNumber(eBean.getPhoneNumber());
			empBean.setBirthdate(eBean.getBirthdate());
			empBean.setAddress(eBean.getAddress());
			empBean.setNote(eBean.getNote());
		}
		return empBean;
	}

	public boolean deleteOne(int eId) {
		Session session = sessionFactory.getCurrentSession();
		EmployeeBean empBean = session.get(EmployeeBean.class, eId);
		if (empBean != null) {
			session.delete(empBean);
			return true;
		} else {
			return false;
		}
	}
}
