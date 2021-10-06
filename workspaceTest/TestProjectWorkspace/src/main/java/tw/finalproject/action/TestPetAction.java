package tw.finalproject.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.finalproject.model.PetBean;
import tw.finalproject.model.PetDAO;
import tw.finalproject.util.HibernateUtil;

public class TestPetAction {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

//單一查詢
//		try {
//			session.beginTransaction();
//			
//			PetDAO petDAO = new PetDAO(session);
//			PetBean temp = petDAO.select(1001);
//			
//			if(temp != null) {
//				System.out.println(temp.toString());
//				
//			}else {
//				System.out.println("No Result !");
//			}
//			
//			
//			session.getTransaction().commit();
//			System.out.println("select success !");
//		}catch(Exception e){
//			session.getTransaction().rollback();
//			System.out.println("ERROR,transaction rolled back");
//		}finally {
//			HibernateUtil.closeSessionFactory();
//		}

//單一新增
//		try {
//			session.beginTransaction();
//			PetDAO petDAO = new PetDAO(session);
//			
//			petDAO.insert(new PetBean(1003,"EXP","實驗體","無","實驗體二號","幼年",null,"2nd insert test(identity)","未領養",null,null,null));
//			
//			session.getTransaction().commit();
//			System.out.println("insert success !");
//		}catch(Exception e) {
//			session.getTransaction().rollback();
//			System.out.println("SOMETHING ERROR,transaction rolled back");
//			e.printStackTrace();
//		}finally {
//			HibernateUtil.closeSessionFactory();
//		}

//單一刪除

//		try {
//			session.beginTransaction();
//			PetDAO petDAO = new PetDAO(session);
//			
//			petDAO.deleteById(1003);
//			
//			session.getTransaction().commit();
//			System.out.println("delete success !");
//		}catch(Exception e) {
//			session.getTransaction().rollback();
//			System.out.println("SOMETHING ERROR,transaction rolled back");
//			e.printStackTrace();
//		}finally {
//			HibernateUtil.closeSessionFactory();
//		}

//查詢全部
		try {
			session.beginTransaction();
			PetDAO petDAO = new PetDAO(session);
			
			List<PetBean> allPet = petDAO.selectAll(); 
			
			for(PetBean singlePet : allPet) {
				System.out.println(singlePet.toString());
			}
			

			session.getTransaction().commit();
			System.out.println("selectAll success !");
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("SOMETHING ERROR,transaction rolled back");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

	}

}
