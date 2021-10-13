package tw.finalspring.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional
public class BookingsDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
//插入單筆資料
	public BookingsBean insertOne(BookingsBean temp) {
		Session session = sessionFactory.openSession();
		
		if(temp!=null) {
			session.save(temp);
		}	
		session.close();
		return temp;
	}
	
//載入全部資料
	public List<BookingsBean> selectAll(){
		Session session = sessionFactory.getCurrentSession();
		System.out.println("BookingsDAO do selectALL");
		Query<BookingsBean> query = session.createQuery("from BookingsBean",BookingsBean.class);
		System.out.println("BookingsDAO finish selectALL");
		
		return query.list(); //因為query只能讀取一次所以使用getCurrentSession,若使用OpenSession再手動Close會造成query無法讀取
		
	}

//使用ID查詢
	public BookingsBean selectById(int bookingsID) {
		Session session =sessionFactory.getCurrentSession();
		BookingsBean temp = session.get(BookingsBean.class,bookingsID);
		
		return temp;	
	}
	
//使用Id刪除
	public boolean deleteById(int bookingsID) {
		Session session = sessionFactory.getCurrentSession();
		BookingsBean temp = session.get(BookingsBean.class,bookingsID);
		if(temp != null) {
			session.delete(temp);
			return true;
		}
		return false;	
	}

//更新單筆資料
	public BookingsBean updateOne(int bookingsID,BookingsBean temp) {
		Session session = sessionFactory.getCurrentSession();
		BookingsBean check = session.get(BookingsBean.class, bookingsID);
		if(check != null) {
			check.setBean(temp);
			return check;
		}
		
		return null;
	}
	
}
