package tw.finalspring.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional
public class PetDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
//插入單筆資料
	public PetBean insertOne(PetBean temp) {
		Session session = sessionFactory.openSession();
		
		if(temp!=null) {
			session.save(temp);
		}	
		session.close();
		return temp;
	}
	
//載入全部資料
	public List<PetBean> selectAll(){
		Session session = sessionFactory.getCurrentSession();
		System.out.println("PetDAO do selectALL");
		Query<PetBean> query = session.createQuery("from PetBean",PetBean.class);
		System.out.println("PetDAO finish selectALL");
		
		return query.list(); //因為query只能讀取一次所以使用getCurrentSession,若使用OpenSession再手動Close會造成query無法讀取
		
	}

//使用ID查詢
	public PetBean selectById(int petId) {
		Session session =sessionFactory.getCurrentSession();
		PetBean temp = session.get(PetBean.class,petId);
		
		return temp;	
	}
	
//使用Id刪除
	public boolean deleteById(int petId) {
		Session session = sessionFactory.getCurrentSession();
		PetBean temp = session.get(PetBean.class,petId);
		if(temp != null) {
			session.delete(temp);
			return true;
		}
		return false;	
	}

//更新單筆資料
	public PetBean updateOne(int petId,PetBean temp) {
		Session session = sessionFactory.getCurrentSession();
		PetBean check = session.get(PetBean.class, petId);
		if(check != null) {
			check.setBean(temp);
			return check;
		}
		
		return null;
	}
	
}
