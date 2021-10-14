package tw.finalspring.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional
public class AnnouncementsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	//插入單筆資料
		public AnnouncementsBean insertOne(AnnouncementsBean temp) {
			Session session = sessionFactory.openSession();
			
			if(temp!=null) {
				session.save(temp);
			}	
			session.close();
			return temp;
		}
		
	//載入全部資料
		public List<AnnouncementsBean> selectAll(){
			Session session = sessionFactory.getCurrentSession();
			System.out.println("AnnouncementsDAO do selectALL");
			Query<AnnouncementsBean> query = session.createQuery("from AnnouncementsBean",AnnouncementsBean.class);
			System.out.println("AnnouncementsDAO finish selectALL");
			
			return query.list(); //因為query只能讀取一次所以使用getCurrentSession,若使用OpenSession再手動Close會造成query無法讀取
			
		}
		
		
		//使用ID查詢
		public AnnouncementsBean selectById(int announceID) {
			Session session =sessionFactory.getCurrentSession();
			AnnouncementsBean temp = session.get(AnnouncementsBean.class,announceID);
			
			return temp;	
		}	
		
		//使用Id刪除
		public boolean deleteById(int announceID) {
			Session session = sessionFactory.getCurrentSession();
			AnnouncementsBean temp = session.get(AnnouncementsBean.class,announceID);
			if(temp != null) {
				session.delete(temp);
				return true;
			}
			return false;	
		}

		
		//更新單筆資料
		public AnnouncementsBean updateOne(int announceID,AnnouncementsBean temp) {
			Session session = sessionFactory.getCurrentSession();
			AnnouncementsBean check = session.get(AnnouncementsBean.class, announceID);
			if(check != null) {
				check.setBean(temp);
				return check;
			}
			
			return null;
		}
		
		
		
		
}
