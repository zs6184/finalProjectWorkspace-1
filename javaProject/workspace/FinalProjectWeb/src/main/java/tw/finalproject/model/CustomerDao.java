package tw.finalproject.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class CustomerDao implements CustomerDaoInterface {

	private Session session;
	
	public CustomerDao(Session session) {
		this.session = session;
	}
	
	
	@Override
	public CustomerBean select(int cId) {
		return session.get(CustomerBean.class, cId);
	}
	

	@Override
	public List<CustomerBean> selectUsername(CustomerBean username) {
		String hql = "from CustomerBean as c where c.cusUsername = :username";
		Query<CustomerBean> query = session.createQuery(hql,CustomerBean.class).setParameter("username", username.getCusUsername());
		List<CustomerBean> list = query.getResultList();
		return list;
	}


	@Override
	public List<CustomerBean> selectAll() {
		Query<CustomerBean> query = session.createQuery("from CustomerBean",CustomerBean.class);
		return query.list();
	}

	@Override
	public CustomerBean insert(CustomerBean cBean) {
		CustomerBean cusBean = session.get(CustomerBean.class, cBean.getCusId());
		if(cusBean == null) {
			session.save(cBean);
			return cBean;
		}
		return null;
	}

	@Override
	public CustomerBean updateOne(int cId, CustomerBean cBean) {
		CustomerBean cusBean = session.get(CustomerBean.class, cId);
		if(cusBean != null) {
			//透過Bean取得資料更新到資料庫
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
		}
		return cusBean;
	}
	


	@Override
	public boolean deletOne(int cId) {
		CustomerBean cusBean = session.get(CustomerBean.class, cId);
		if(cusBean != null) {
			session.delete(cusBean);
			return true;
		}else {
			return false;
		}
	}

}
