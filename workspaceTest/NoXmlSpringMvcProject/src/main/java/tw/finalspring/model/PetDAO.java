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
		Query<PetBean> query = session.createQuery("from PetBean",PetBean.class);
		
		return query.list(); //因為query只能讀取一次所以使用getCurrentSession,若使用OpenSession再手動Close會造成query無法讀取
		
	}

//使用ID查詢
	public PetBean selectById(int petId) {
		Session session =sessionFactory.getCurrentSession();
		PetBean temp = session.get(PetBean.class,petId);
		
		return temp;	
	}
	
//使用Id刪除
	public void deleteById(int petId) {
		Session session = sessionFactory.getCurrentSession();
		PetBean temp = session.get(PetBean.class,petId);
		if(temp != null) {
			System.out.println(temp);
			session.delete(temp);	
		}
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
	
//根據類別與性別進行查詢	
	public List<PetBean> searchData(String category,String sex) {
		Session session = sessionFactory.getCurrentSession();
		
		if(category==""&& sex!="") {
			System.out.println("sex= "+sex);
			Query<PetBean> query = session.createQuery("from PetBean where sex=:sex",PetBean.class);
			query.setParameter("sex", sex);
			System.out.println("資料筆數:"+query.list().size());
			return query.list();
		}
		if(sex==""&&category!=""){
			System.out.println("sex= "+sex);
			Query<PetBean> query = session.createQuery("from PetBean where category=:category",PetBean.class);
			query.setParameter("category", category);
			System.out.println("資料筆數:"+query.list().size());
			
			return query.list();
		}
		if(sex!=""&&category!=""){
			System.out.println("sex= "+sex);
			Query<PetBean> query = session.createQuery("from PetBean where category=:category and sex=:sex",PetBean.class);
			query.setParameter("category", category);
			query.setParameter("sex", sex);

			return query.list();
		}

			return null;
	}
}
