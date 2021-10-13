package iii.homework.service;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iii.homework.model.CustomerBean;
import iii.homework.model.CustomerDao;

@Service @Transactional
public class CustomerService{
//	 implements CustomerServiceInterface
	@Autowired
	private CustomerDao cusDao;


	// 透過id搜尋
//	@Override
	public CustomerBean select(int cId) {
		CustomerBean theCus = cusDao.select(cId);
		return theCus;
	}

	// 搜尋全部會員資料
//	@Override
	public List<CustomerBean> selectAll() {
		List<CustomerBean> cusAll = cusDao.selectAll();

		return cusAll;
	}
	
	
	// 登入時查詢會員真實名稱
//	@Override
	public String selectUsernameLogin(CustomerBean cBean) {
//		String cusPassword = cBean.getCusPassword();// 先取得使用者輸入的密碼
		Query<CustomerBean> theCus = cusDao.selectUsernameLogin(cBean);// 透過DAO取的使用者資料
		CustomerBean result = theCus.uniqueResult();
		// 判斷帳號是否存在
		result.getCusRealname();
		return result.getCusRealname();

	}
	

	// 登入時帳號密碼驗證
//	@Override
	public String selectUsername(CustomerBean cBean) {
		String cusPassword = cBean.getCusPassword();// 先取得使用者輸入的密碼
		List<CustomerBean> theCus = cusDao.selectUsername(cBean);// 透過DAO取的使用者資料

		// 判斷帳號是否存在
		if (theCus.isEmpty()) {
			System.out.println("無此帳號");
			return "fail";
		} else {
			// 利用forEach將密碼取出
			for (CustomerBean oneCus : theCus) {
				String getPasswordDB = oneCus.getCusPassword();
				// 判斷密碼是否正確
				if (cusPassword.equals(getPasswordDB)) {
					System.out.printf("歡迎 %s 成功登入\n", oneCus.getCusRealname());
					return "pass";
				} else {
					System.out.println("密碼錯誤");
				}
			}
		}
		return "fail";

	}

	// 會員註冊時判斷帳號是否存在
//	@Override
	public String selectCreateCusUsername(CustomerBean cBean) {
		String cusUsername = cBean.getCusUsername();
		List<CustomerBean> theCus = cusDao.selectUsername(cBean);
		if (theCus.isEmpty()) {
			System.out.println("usernamePass");
			return "pass";
		}
		System.out.println("usernameFail");
		return "fail";
	}

	// 會員註冊時判斷電話號碼是否存在
//	@Override
	public String selectPhone(CustomerBean cBean) {
		String phone = cBean.getPhoneNumber();
		List<CustomerBean> theCus = cusDao.selectPhone(cBean);
		if (theCus.isEmpty()) {
			System.out.println("phonePass");
			return "pass";
		}
		System.out.println("phoneFail");
		return "fail";
	}

	// 會員註冊時判斷Email是否存在
//	@Override
	public String selectEmail(CustomerBean cBean) {
		String email = cBean.getEmail();
		List<CustomerBean> theCus = cusDao.selectEmail(cBean);
		if (theCus.isEmpty()) {
			System.out.println("emailPass");
			return "pass";
		}
		System.out.println("emailFail");
		return "fail";
	}

	// 新增
//	@Override
	public CustomerBean insert(CustomerBean cBean) {
		CustomerBean oneCus = cusDao.insert(cBean);
		return null;
	}

	// 更新
//	@Override
	public CustomerBean updateOne(int cId, CustomerBean cBean) {
		CustomerBean oneCus = cusDao.updateOne(cId, cBean);
		return oneCus;
	}

	// 刪除
//	@Override
	public boolean deletOne(int cId) {
		boolean boo = cusDao.deletOne(cId);
		return boo;
	}

}
