package tw.finalproject.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class PetDAO implements PetDAOInterface {

	private Session session;

	public PetDAO(Session session) {
		this.session = session;	
	}
	
	@Override
	public PetBean insert(PetBean petBean) {
			session.save(petBean);
			
			return petBean;
	}

	@Override
	public PetBean select(int petId) {
		return session.get(PetBean.class, petId);
	}

	@Override
	public boolean deleteById(int petId) {
		PetBean temp = session.get(PetBean.class, petId);

		if (temp != null) {
			session.delete(temp);
			return true;
		}
		return false;
	}

	@Override
	public List<PetBean> selectAll() {
		Query<PetBean> query = session.createQuery("from PetBean",PetBean.class);
		return query.list();
	}

	@Override
	public PetBean updateOne(int petId,PetBean petBean) {
		PetBean DBBean = session.get(PetBean.class, petId);
		
		if(DBBean != null) {
			DBBean.setBean(petBean);
		
			return DBBean;
		}
		return null;
	}
	
	

}
