package tw.finalproject.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import tw.finalproject.model.CustomerBean;
import tw.finalproject.util.HibernateUtil;

public class AddCusTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
//		try {
//			session.beginTransaction();
//			CustomerBean cBean = new CustomerBean();
//			CustomerBean cBean1 = new CustomerBean();
			//新增
//			cBean.setCusUsername("qqqqqq");
//			cBean.setCusPassword("aaaaaa");
//			cBean.setAka("小胖");
//			cBean.setCusRealname("王小明");
//			cBean.setGender("男");
//			cBean.setPhoneNumber("0987654432");
//			cBean.setEmail("qqq@gmail.com");
//			cBean.setBirthdate("1999-09-02");
//			cBean.setAddress("高雄市");
//			cBean1.setCusUsername("cccccc");
//			cBean1.setCusPassword("dddddd");
//			cBean1.setCusRealname("賈斯汀");
//			cBean1.setAka("丁丁");
//			cBean1.setGender("男");
//			cBean1.setPhoneNumber("0912345678");
//			cBean1.setEmail("justin@gmail.com");
//			cBean1.setBirthdate("1995-11-01");
//			cBean1.setAddress("高雄市");
//			
//			CustomerDao cDao = new CustomerDao(session);
//			cDao.insert(cBean);
//			cDao.insert(cBean1);
			
//			//查詢
//			CustomerBean tempCustomer = cDao.select(2);
//			if(tempCustomer != null) {
//				System.out.println(tempCustomer.getCusId()+" : "+tempCustomer.getCusUsername());
//			}else {
//				System.out.println("No Result");
//			}
			
			//查詢全部
//			List<MemberBean> mAll = mDao.selectAll();
//			for(MemberBean m:mAll) {
//				System.out.println(m.getMemberId()+" : "+m.getMemberName());
//			}
//		CustomerBean cusBean = new CustomerBean();
//		cusBean.setCusUsername("aaa");
//		CustomerService cusServicse = new CustomerService(session);
//		List<CustomerBean> list = cusServicse.selectUsername(cusBean);
//		for(CustomerBean a:list) {
//			System.out.println(a);
//		}
//			
//			session.getTransaction().commit();
//			System.out.println("Commit OK");
//		}catch(Exception e){
//			session.getTransaction().rollback();
//			System.out.println("Something Wrong and Rollback");
//			e.printStackTrace();
//		}finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		
		
		
		
		AddCusTest addM = new AddCusTest();
		
		addM.hqlSelectSalary2();
		
//		CustomerBean cusBean = new CustomerBean();
//		cusBean.setCusUsername("aaa");
//		CustomerService cusServicse = new CustomerService(session);
//		List<CustomerBean> list = cusServicse.selectUsername(cusBean);
//		for(CustomerBean a:list) {
//			System.out.println(a);
//		}
		
		
	}

	private void hqlSelectSalary2() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		
		try {
			session.beginTransaction();
			CustomerBean cusBean = new CustomerBean();
			cusBean.setCusUsername("asd");
			//自定義參數使用[:參數名稱]
			String hql = "from CustomerBean as c where c.cusUsername = :username";
			Query<CustomerBean> query = session.createQuery(hql,CustomerBean.class)
					.setParameter("username", cusBean.getCusUsername());
			List<CustomerBean> list = query.getResultList();
			for(CustomerBean oneCus:list) {
				System.out.println(oneCus);
			}
			if(list.isEmpty()) {
				System.out.println("空");
			}else {
				System.out.println("有");
			}
			
			session.getTransaction().commit();
		}catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSessionFactory();
		}
	
	}
	
	
	
	
}
