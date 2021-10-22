package iii.homework.service;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iii.homework.model.CustomerBean;
import iii.homework.model.CustomerDao;

@Service @Transactional
public class CustomerService {
	
	@Autowired
	private CustomerDao cusDao;

	// 登入時查詢會員真實名稱
//	public String selectUsernameLogin(CustomerBean cBean) {
//		Query<CustomerBean> theCus = cusDao.selectUsernameLogin(cBean);// 透過DAO取的使用者資料
//		CustomerBean result = theCus.uniqueResult();
//		return	result.getCusRealname();

	// 登入時查詢會員
	public CustomerBean selectUsernameLogin(CustomerBean cBean) {
		//String cusPassword = cBean.getCusPassword();// 先取得使用者輸入的密碼
		Query<CustomerBean> theCus = cusDao.selectUsernameLogin(cBean);// 透過DAO取的使用者資料
		CustomerBean result = theCus.uniqueResult();
		// 判斷帳號是否存在
		return result;
	}

	// 登入時帳號密碼驗證
	public boolean selectUsername(CustomerBean cBean) {
		String cusPassword = cBean.getCusPassword();// 先取得使用者輸入的密碼
		List<CustomerBean> theCus = cusDao.selectUsername(cBean);

		// 判斷帳號是否存在
		if (theCus.isEmpty()) {
			System.out.println("無此帳號");
			return false;
		} else {
			// 利用forEach將密碼取出
			for (CustomerBean oneCus : theCus) {
				String getPasswordDB = oneCus.getCusPassword();
				// 判斷密碼是否正確
				if (cusPassword.equals(getPasswordDB)) {
					System.out.printf("歡迎 %s 成功登入\n", oneCus.getCusRealname());
					return true;
				} else {
					System.out.println("密碼錯誤");
				}
			}
		}
		return false;
	}

	// 會員註冊時判斷帳號是否存在
	public boolean selectCreateCusUsername(CustomerBean cBean) {
		List<CustomerBean> theCus = cusDao.selectUsername(cBean);
		if (theCus.isEmpty()) {
			System.out.println("usernamePass");
			return true;
		}
		System.out.println("usernameFail");
		return false;
	}

	// 新增
	public CustomerBean insert(CustomerBean cBean) {
		cusDao.insert(cBean);
		return null;
	}
	
	//用Id抓CustomerBean
	public CustomerBean selectById(int cusId) {
		CustomerBean temp = cusDao.selectById(cusId);
		return temp;
	}
	
	//修改個人資料
	public CustomerBean updateOne(int cusId,CustomerBean temp) {
		return cusDao.updateOne(cusId,temp);
	}
	
	//刪除個人資料
	public void deleteOne(int cusId) {
		cusDao.deleteOne(cusId);
	}
}
