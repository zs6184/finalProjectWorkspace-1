package tw.springbootfinal.users.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.springbootfinal.users.exception.UserNotFoundException;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository empReps;

	// 透過ID查詢指定員工
	public EmployeeBean findById(int eId) {
		Optional<EmployeeBean> op1 = empReps.findById(eId);
		return op1.get();
	}
	
	//查詢指定帳號員工
	public EmployeeBean getByEmpUsername(String empUsername) {
		Optional<EmployeeBean> op1 = empReps.getByEmpUsername(empUsername);
		
		if(op1.isEmpty()) {
			throw new UserNotFoundException("Can't Find User;");
		}
		return op1.get();
	}


	// 查詢全部
	public List<EmployeeBean> findAll() {
		return empReps.findAll();
	}

	// 取得員工帳號最大值
	public String findMaxEmpUsername() {
		// 搜尋資料庫內帳號最大值
		List<EmployeeBean> findMaxEmpUsername = empReps.findMaxEmpUsername();
		String maxUsername = "";// 建立區域變數
		LocalDate now = LocalDate.now();// 取得今天日期
		String nowDate = String.format("%tY%<tm%<td", now);
		String day = String.format("%td", now); // 取得day
		String username = "EMP" + nowDate;// EMP20211012
		if(findMaxEmpUsername.isEmpty()) {
			String newUsername = username + "01";// 建立今天日期順序為01的帳號
			return newUsername;
		}
		for (EmployeeBean employeeBean : findMaxEmpUsername) {
			maxUsername = employeeBean.getEmpUsername();
		}
		String maxDay = maxUsername.substring(9, 11);// 取得最大值的日期 EMP202110"12"02
		


		if (maxUsername == null || maxUsername == "") {// 如果帳號日期與今天日期相同
			String newUsername = username + "01";// 建立今天日期順序為01的帳號
			return newUsername;
		} else if (day.equals(maxDay)) {
			String strNumber = maxUsername.substring(11);// 取得最後兩碼順序
			int intNumber = Integer.parseInt(strNumber) + 1;// 轉換成數字+1
			String newUsername = String.format("%s%02d", username, intNumber);
			System.out.println(newUsername);// 組合成最新帳號
			return newUsername;
		} else {
			String newUsername = username + "01";// 建立今天日期順序為01的帳號
			return newUsername;
		}
	}

	// 員工註冊時判斷電話號碼是否存在
	public String findByPhoneNumber(EmployeeBean eBean) {
		String phone = eBean.getPhoneNumber();
		List<EmployeeBean> theEmp = empReps.findByPhoneNumber(phone);
		if (theEmp.isEmpty()) {
			System.out.println("phonePass");
			return "pass";
		}
		System.out.println("phoneFail");
		return "fail";
	}

	// 新增
	public EmployeeBean save(EmployeeBean eBean) {
		return empReps.save(eBean);
	}

	// 刪除
	public boolean deleteById(int eId) {
		empReps.deleteById(eId);
		return true;
	}

}
