package tw.finalspring.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional
public class CustomerDao {
//	 implements CustomerDaoInterface
	@Autowired
    private SessionFactory sessionFactory;

//	@Override
	public CustomerBean select(int cId) {
		Session session = sessionFactory.getCurrentSession();
		CustomerBean cusBean = session.get(CustomerBean.class, cId);
		return cusBean;
	}

//	@Override
	public List<CustomerBean> selectAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<CustomerBean> query = session.createQuery("from CustomerBean", CustomerBean.class);
		return query.list();
	}
	
	// 搜尋會員帳號
//	@Override
	public Query<CustomerBean> selectUsernameLogin(CustomerBean username) {
		String hql = "from CustomerBean as c where c.cusUsername = :username";
		Session session = sessionFactory.getCurrentSession();
		Query<CustomerBean> query = session.createQuery(hql, CustomerBean.class).setParameter("username",
				username.getCusUsername());
		return query;
	}
	

	// 搜尋會員帳號
//	@Override
	public List<CustomerBean> selectUsername(CustomerBean username) {
		String hql = "from CustomerBean as c where c.cusUsername = :username";
		Session session = sessionFactory.getCurrentSession();
		Query<CustomerBean> query = session.createQuery(hql, CustomerBean.class).setParameter("username",
				username.getCusUsername());
		List<CustomerBean> list = query.getResultList();
		return list;
	}

	// 會員註冊時搜尋電話號碼
//	@Override
	public List<CustomerBean> selectPhone(CustomerBean phoneNumber) {
		String hql = "from CustomerBean as c where c.phoneNumber = :phoneNumber";
		Session session = sessionFactory.getCurrentSession();
		Query<CustomerBean> query = session.createQuery(hql, CustomerBean.class).setParameter("phoneNumber",
				phoneNumber.getPhoneNumber());
		List<CustomerBean> list = query.getResultList();
		return list;
	}

	// 會員註冊時搜尋Email
//	@Override
	public List<CustomerBean> selectEmail(CustomerBean email) {
		String hql = "from CustomerBean as c where c.email = :email";
		Session session = sessionFactory.getCurrentSession();
		Query<CustomerBean> query = session.createQuery(hql, CustomerBean.class).setParameter("email",
				email.getEmail());
		List<CustomerBean> list = query.getResultList();
		return list;
	}

//	@Override
	public CustomerBean insert(CustomerBean cBean) {
		Session session = sessionFactory.getCurrentSession();
		CustomerBean cusBean = session.get(CustomerBean.class, cBean.getCusId());
		if (cusBean == null) {
			session.save(cBean);
			return cBean;
		}
		return null;
	}

//	@Override
	public CustomerBean updateOne(int cId, CustomerBean cBean) {
		Session session = sessionFactory.getCurrentSession();
		CustomerBean cusBean = session.get(CustomerBean.class, cId);
		if (cusBean != null) {
			// 透過Bean取得資料更新到資料庫
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
	
	//更新會員中心個人資料
	public CustomerBean updateCustomerCenter(int cId, CustomerBean cBean) {
		Session session = sessionFactory.getCurrentSession();
		CustomerBean cusBean = session.get(CustomerBean.class, cId);
		if (cusBean != null) {
			// 透過Bean取得資料更新到資料庫
			cusBean.setCusRealname(cBean.getCusRealname());
			cusBean.setAka(cBean.getAka());
			cusBean.setGender(cBean.getGender());
			cusBean.setPhoneNumber(cBean.getPhoneNumber());
			cusBean.setEmail(cBean.getEmail());
			cusBean.setBirthdate(cBean.getBirthdate());
			cusBean.setAddress(cBean.getAddress());
		}
		return cusBean;
	}
	

//	@Override
	public boolean deletOne(int cId) {
		Session session = sessionFactory.getCurrentSession();
		CustomerBean cusBean = session.get(CustomerBean.class, cId);
		if (cusBean != null) {
			session.delete(cusBean);
			return true;
		} else {
			return false;
		}
	}
	
	//更新圖片
	public String saveFile(int cId, String fileName, String saveFilePath) {
		Session session = sessionFactory.getCurrentSession();
		CustomerBean cusBean = session.get( CustomerBean.class, cId);
		
		
		try {
			FileInputStream fis = new FileInputStream(saveFilePath);
			byte[] b1 = new byte[fis.available()];
			
			fis.read(b1);//將讀取檔案放入byte陣列
			fis.close();
			
			cusBean.setImageName(fileName);
			cusBean.setImage(b1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "save";
	}
	
	//會員中心圖片下載到專案資料夾底下的downloadTempDir
	public String imageDownload(byte[] image, String filePath) {
		try { 
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(image);
			fos.close();
			System.out.println("輸出完成");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "download";
	}

}
