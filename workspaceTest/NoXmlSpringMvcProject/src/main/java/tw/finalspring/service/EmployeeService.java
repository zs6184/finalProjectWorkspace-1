package tw.finalspring.service;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.finalspring.model.EmployeeBean;
import tw.finalspring.model.EmployeeDao;

@Service @Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeDao empDao;
	
	public EmployeeBean select(int eId) {
		return empDao.select(eId);
	}

	public List<EmployeeBean> selectAll() {
		return empDao.selectAll();
	}
	
	//取得員工帳號最大值
	public String selectMaxUsername() {
		String maxUsername = empDao.selectMaxUsername();//搜尋資料庫內帳號最大值
		String maxDay = maxUsername.substring(9, 11);//取得最大值的日期 EMP202110"12"02
		LocalDate now = LocalDate.now();//取得今天日期
		String nowDate = String.format("%tY%<tm%<td", now);
		String day = String.format("%td", now); //取得day
		String username = "EMP"+nowDate;//EMP20211012
//		if(day.equals(maxDay)) {//如果帳號日期與今天日期相同
//			String strNumber = maxUsername.substring(11);//取得最後兩碼順序
//			int intNumber = Integer.parseInt(strNumber)+1;//轉換成數字+1
//			String newUsername = String.format("%s%02d", username,intNumber);
//			System.out.println(newUsername);//組合成最新帳號
//			return newUsername;
//		}else {
//			String newUsername = username+"01";//建立今天日期順序為01的帳號
//			return newUsername;
//		}
		if(maxUsername==null || maxUsername=="") {//如果帳號日期與今天日期相同
			String newUsername = username+"01";//建立今天日期順序為01的帳號
			return newUsername;
		}else if(day.equals(maxDay)){
			String strNumber = maxUsername.substring(11);//取得最後兩碼順序
			int intNumber = Integer.parseInt(strNumber)+1;//轉換成數字+1
			String newUsername = String.format("%s%02d", username,intNumber);
			System.out.println(newUsername);//組合成最新帳號
			return newUsername;
		}else {
			String newUsername = username+"01";//建立今天日期順序為01的帳號
			return newUsername;
		}
	}

	
	public String selectUsernameLogin(EmployeeBean username) {
		Query<EmployeeBean> theCus = empDao.selectUsernameLogin(username);// 透過DAO取的使用者資料
		EmployeeBean result = theCus.uniqueResult();
		// 判斷帳號是否存在
		result.getEmpRealname();
		return result.getEmpRealname();
	}

	// 登入時帳號密碼驗證
		public String selectUsername(EmployeeBean eBean) {
			String empPassword = eBean.getEmpPassword();// 先取得使用者輸入的密碼
			List<EmployeeBean> theEmp = empDao.selectUsername(eBean);// 透過DAO取的使用者資料

			// 判斷帳號是否存在
			if (theEmp.isEmpty()) {
				System.out.println("無此帳號");
				return "fail";
			} else {
				// 利用forEach將密碼取出
				for (EmployeeBean oneEmp : theEmp) {
					String getPasswordDB = oneEmp.getEmpPassword();
					// 判斷密碼是否正確
					if (empPassword.equals(getPasswordDB)) {
						System.out.printf("歡迎 %s 成功登入\n", oneEmp.getEmpRealname());
						return "pass";
					} else {
						System.out.println("密碼錯誤");
					}
				}
			}
			return "fail";

		}

		// 會員註冊時判斷帳號是否存在
		public String selectCreateEmpUsername(EmployeeBean eBean) {
			String empUsername = eBean.getEmpUsername();
			List<EmployeeBean> theEmp = empDao.selectUsername(eBean);
			if (theEmp.isEmpty()) {
				System.out.println("usernamePass");
				return "pass";
			}
			System.out.println("usernameFail");
			return "fail";
		}

		// 會員註冊時判斷電話號碼是否存在
		public String selectPhone(EmployeeBean eBean) {
			String phone = eBean.getPhoneNumber();
			List<EmployeeBean> theEmp = empDao.selectPhone(eBean);
			if (theEmp.isEmpty()) {
				System.out.println("phonePass");
				return "pass";
			}
			System.out.println("phoneFail");
			return "fail";
		}

		// 新增
		public EmployeeBean insert(EmployeeBean eBean) {
			EmployeeBean oneEmp = empDao.insert(eBean);
			return oneEmp;
		}

		// 更新
		public EmployeeBean updateOne(int eId, EmployeeBean eBean) {
			EmployeeBean oneEmp = empDao.updateOne(eId, eBean);
			return oneEmp;
		}

		// 刪除
		public boolean deleteOne(int eId) {
			boolean boo = empDao.deleteOne(eId);
			return boo;
		}
}
