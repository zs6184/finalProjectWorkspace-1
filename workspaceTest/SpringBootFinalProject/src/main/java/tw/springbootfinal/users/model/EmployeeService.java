package tw.springbootfinal.users.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

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
	
	//查詢指定帳號員工
	public List<EmployeeBean> findByEmpUsername(String empUsername) {
		List<EmployeeBean> theEmp = empReps.findByEmpUsername(empUsername);
		return theEmp;
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
	
	// 會員註冊時判斷Email是否存在
		public String findByEmail(EmployeeBean eBean) {
			String email = eBean.getEmail();
			List<EmployeeBean> theEmp = empReps.findByEmail(email);
			if (theEmp.isEmpty()) {
				System.out.println("emailPass");
				return "pass";
			}
			System.out.println("emailFail");
			return "fail";
		}
		
		// 會員註冊時判斷Email是否存在
		public List<EmployeeBean> findByEmail(String email) {
			List<EmployeeBean> theEmp = empReps.findByEmail(email);
			return theEmp;
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
	// 進入變更密碼時取得圖片
		public String SelectUserImage(String username, Model m, HttpServletRequest request) {

			EmployeeBean empBean = getByEmpUsername(username);
			// m.addAttribute("cus", cusBean);// 將結果設成屬性給jsp使用

			int empId = empBean.getEmpId();
			String imageName = empBean.getImageName();
			byte[] image = empBean.getImage();

			// 如果會員沒有上傳過圖片就使用預設圖片
			if (image == null) {
				m.addAttribute("imageName", "husky.jpg");
			} else {
				// 抓到專案路徑加上暫存資料夾名稱
				String path = request.getSession().getServletContext().getRealPath("/") + "downloadTempDir\\";
				System.out.println(path);
				imageDownload(image, empId, imageName, path);

				m.addAttribute("imageName", imageName);
				m.addAttribute("cusId", empId);
			}
			return "checkPassword";
		}
	
	//抓取照片
		public String selectImage(String username,HttpServletRequest request) {
			//此處無法使用@RequestParam("id")來抓取id，網頁會陷入過多重新導向問題，因此改採session方式抓會員資料
			EmployeeBean empBean = getByEmpUsername(username);
			// 取得資料庫資料
			int empId = empBean.getEmpId();
			String imageName = empBean.getImageName();
			byte[] image = empBean.getImage();
			System.out.println("imageName: " + imageName);
			// 如果會員沒有上傳過圖片就使用預設圖片
			if (image == null) {
				System.out.println("照片名null");
				return "husky.jpg";
			} else {
				// 抓到專案路徑加上暫存資料夾名稱
				String path = request.getSession().getServletContext().getRealPath("/") + "downloadTempDir\\";
				System.out.println(path);
				imageDownload(image, empId, imageName, path);
				return imageName;
			}
		}
		
		// 更新圖片
		public String saveFile(int cId, EmployeeBean empBean, String saveFilePath) {
			try {
				FileInputStream fis = new FileInputStream(saveFilePath);
				byte[] b1 = new byte[fis.available()];

				fis.read(b1);// 將讀取檔案放入byte陣列
				fis.close();

				System.out.println(empBean.getEmpId());
				System.out.println(empBean.getEmpRealname());
				System.out.println(empBean.getEmail());

				empBean.setImage(b1);
				empReps.save(empBean);

			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
		}
	
	public String imageDownload(byte[] image, int empId, String imageName, String path) {
		// 建立資料夾
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		String profix = empId + "_"; // 建立檔案前綴檔名
		File[] listFiles = file.listFiles();// 將資料夾內的圖片存成陣列
		for (int i = 0; i < listFiles.length; i++) {
			// 如果圖片的名稱.startsWith(profix)符合cusId+"_"
			if (listFiles[i].getName().startsWith(profix)) {
				File deleteFile2 = new File(path + listFiles[i].getName());// 建立要刪除的檔案路徑
				deleteFile2.delete();
			}
		}

		String filePath1 = path + imageName;
		try {
			FileOutputStream fos = new FileOutputStream(filePath1);
			fos.write(image);
			fos.close();
			System.out.println("輸出完成");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "PASS";
	}
	
	//會員忘記密碼或更換信箱時查詢身分用
	public EmployeeBean getBySecretkey(String secretkey) {
		Optional<EmployeeBean> op1 = empReps.getBySecretkey(secretkey);
		if(op1.isEmpty()) {
			throw new UserNotFoundException("Can't Find Secretkey;");// 自定義例外
		}
		return op1.get();
	}
	
	//會員忘記密碼或更換信箱時查詢身分用
	public List<EmployeeBean> findBySecretkey(String secretkey) {
		List<EmployeeBean> findBySecretkey = empReps.findBySecretkey(secretkey);
		return findBySecretkey;
	}

}
