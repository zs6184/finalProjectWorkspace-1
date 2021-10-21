package tw.finalspring.model;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Repository @Transactional
public class AnnouncementsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	//插入單筆資料
		public AnnouncementsBean insertOne(AnnouncementsBean temp,MultipartFile picture) throws IOException {
			Session session = sessionFactory.openSession();
			
			if(temp!=null) {
				byte[] picBytes = picture.getBytes();
				temp.setPicture(picBytes);
				session.save(temp);
			}	
			session.close();
			return temp;
		}
		
	//載入全部資料
		public List<AnnouncementsBean> selectAll(){
			Session session = sessionFactory.getCurrentSession();
			
			Query<AnnouncementsBean> query = session.createQuery("from AnnouncementsBean",AnnouncementsBean.class);
		
			
			return query.list(); //因為query只能讀取一次所以使用getCurrentSession,若使用OpenSession再手動Close會造成query無法讀取
			
		}
		
		
		//使用ID查詢
		public AnnouncementsBean selectById(int announceID) {
			Session session =sessionFactory.getCurrentSession();
			AnnouncementsBean temp = session.get(AnnouncementsBean.class,announceID);
			
			return temp;	
		}	
		
		//使用Id刪除
		public void deleteById(int announceID) {
			Session session = sessionFactory.getCurrentSession();
			AnnouncementsBean temp = session.get(AnnouncementsBean.class,announceID);
			if(temp != null) {
				session.delete(temp);
				
			}
			
		}

		
		//更新單筆資料
		public AnnouncementsBean updateOne(int announceID,AnnouncementsBean temp,MultipartFile picture) throws IOException {
			Session session = sessionFactory.getCurrentSession();
			AnnouncementsBean check = session.get(AnnouncementsBean.class, announceID);
			if(check != null) {
				byte[] picBytes = picture.getBytes();
			
				check.setBean(temp);
				check.setPicture(picBytes);
				return check;
			}
			
			return null;
		}
		
		
		
		
}
