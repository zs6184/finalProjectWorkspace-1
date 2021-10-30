package tw.springbootfinal.users.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.springbootfinal.users.exception.UserNotFoundException;

@Service
@SessionAttributes(value = { "cusId","realName" })
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository cusReps;

	// 透過id搜尋
	public CustomerBean findById(int cId) {
		Optional<CustomerBean> op1 = cusReps.findById(cId);
		if (op1.isEmpty()) {// 如果空的就拋出例外
			throw new UserNotFoundException("Can't Find User;");// 自定義例外
		}
		return op1.get();
	}

	// 搜尋全部會員資料
	public List<CustomerBean> findAll() {
		List<CustomerBean> cusAll = cusReps.findAll();
		return cusAll;
	}

	// 會員中心用username查詢
	public List<CustomerBean> findByCustomerCenterUsername(CustomerBean cusBean) {
		String cusUsername = cusBean.getCusUsername();
		List<CustomerBean> theCus = cusReps.findByCusUsername(cusUsername);
		return theCus;
	}

	// 根據帳號查詢
	public CustomerBean getByCusUsername(String name) {
		Optional<CustomerBean> op1 = cusReps.getByCusUsername(name);
		if (op1.isEmpty()) {// 如果空的就拋出例外
			throw new UserNotFoundException("Can't Find User;");// 自定義例外
		}
		return op1.get();
	}

	// 登入時帳號密碼驗證
	public String findByCusUsername(CustomerBean cusBean) {
		String cusPassword = cusBean.getCusPassword();
		String cusUsername = cusBean.getCusUsername();

		List<CustomerBean> theCus = cusReps.findByCusUsername(cusUsername);
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

	// 登入時查詢會員真實名稱
	public String findByCusUsernameLogin(CustomerBean cusBean) {
		String cusUsername = cusBean.getCusUsername();
		String cusRealname = "";
		List<CustomerBean> theCus = cusReps.findByCusUsername(cusUsername);
		for (CustomerBean customerBean : theCus) {
			cusRealname = customerBean.getCusRealname();
		}
		return cusRealname;
	}

	// 會員註冊時判斷帳號是否存在
	public String findByCreateCusUsername(CustomerBean cBean) {
		String cusUsername = cBean.getCusUsername();
		List<CustomerBean> theCus = cusReps.findByCusUsername(cusUsername);
		if (theCus.isEmpty()) {
			System.out.println("usernamePass");
			return "pass";
		}
		System.out.println("usernameFail");
		return "fail";
	}

	// 會員註冊時判斷電話號碼是否存在
	public String findByPhoneNumber(CustomerBean cBean) {
		String phone = cBean.getPhoneNumber();
		List<CustomerBean> theCus = cusReps.findByPhoneNumber(phone);
		if (theCus.isEmpty()) {
			System.out.println("phonePass");
			return "pass";
		}
		System.out.println("phoneFail");
		return "fail";
	}

	// 會員註冊時判斷Email是否存在
	public String findByEmail(CustomerBean cBean) {
		String email = cBean.getEmail();
		List<CustomerBean> theCus = cusReps.findByEmail(email);
		if (theCus.isEmpty()) {
			System.out.println("emailPass");
			return "pass";
		}
		System.out.println("emailFail");
		return "fail";
	}

	// 新增(沒給id)、更新(有給id)
	public CustomerBean save(CustomerBean cusBean) {
		return cusReps.save(cusBean);
	}

	// 刪除
	public void deleteById(int cId) {
		cusReps.deleteById(cId);
	}

	// 更新圖片
	public String saveFile(int cId, CustomerBean cusBean, String saveFilePath) {
		try {
			FileInputStream fis = new FileInputStream(saveFilePath);
			byte[] b1 = new byte[fis.available()];

			fis.read(b1);// 將讀取檔案放入byte陣列
			fis.close();

			System.out.println(cusBean.getCusId());
			System.out.println(cusBean.getCusRealname());
			System.out.println(cusBean.getEmail());

			cusBean.setImage(b1);
			cusReps.save(cusBean);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	// 從資料庫下載圖片
	public String imageDownload(byte[] image, int cusId, String imageName, String path) {
		// 建立資料夾
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		String profix = cusId + "_"; // 建立檔案前綴檔名
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

	// 透過Session直接返回用戶物件
	public CustomerBean getLoginCustomerBean(HttpSession session) {
		CustomerBean customerBean = new CustomerBean();
		customerBean.setCusUsername((String) session.getAttribute("username"));
		return findByCustomerCenterUsername(customerBean).get(0);
	}

//	// 進入變更密碼時取得圖片
//	public String SelectUserImage(HttpSession session, Model m, HttpServletRequest request) {
//		// 取得登入時session層級的username
//		Object attr = session.getAttribute("username");
//		String str = attr.toString();
//		System.out.println("str" + str);
//		CustomerBean cusBean = new CustomerBean();
//		cusBean.setCusUsername(str);// 設定username到Bean
//		// 利用username取得list結果集
//		List<CustomerBean> cus = findByCustomerCenterUsername(cusBean);
//		m.addAttribute("cus", cus);// 將結果設成屬性給jsp使用
//
//		int cusId = 0;
//		String imageName = null;
//		byte[] image = {};
//		// 取得資料庫資料
//		for (CustomerBean customerBean : cus) {
//			cusId = customerBean.getCusId();
//			imageName = customerBean.getImageName();
//			image = customerBean.getImage();
//			System.out.println("imageName: " + imageName);
//			System.out.println("cusId: " + cusId);
//			System.out.println("image: " + image);
//		}
//		// 如果會員沒有上傳過圖片就使用預設圖片
//		if (image == null) {
//			m.addAttribute("imageName", "husky.jpg");
//		} else {
//			// 抓到專案路徑加上暫存資料夾名稱
//			String path = request.getSession().getServletContext().getRealPath("/") + "downloadTempDir\\";
//			System.out.println(path);
//			imageDownload(image, cusId, imageName, path);
//
//			m.addAttribute("imageName", imageName);
//		}
//
//		return "success";
//	}

	// 進入變更密碼時取得圖片
	public String SelectUserImage(String username, Model m, HttpServletRequest request) {

		CustomerBean cusBean = getByCusUsername(username);
		// m.addAttribute("cus", cusBean);// 將結果設成屬性給jsp使用

		int cusId = cusBean.getCusId();
		String imageName = cusBean.getImageName();
		byte[] image = cusBean.getImage();

		// 如果會員沒有上傳過圖片就使用預設圖片
		if (image == null) {
			m.addAttribute("imageName", "husky.jpg");
		} else {
			// 抓到專案路徑加上暫存資料夾名稱
			String path = request.getSession().getServletContext().getRealPath("/") + "downloadTempDir\\";
			System.out.println(path);
			imageDownload(image, cusId, imageName, path);

			m.addAttribute("imageName", imageName);
			m.addAttribute("cusId", cusId);
		}
		return "checkPassword";
	}
	
	//會員忘記密碼或更換信箱時查詢身分用
	public CustomerBean getByEmail(String email) {
		Optional<CustomerBean> op1 = cusReps.getByEmail(email);
		if(op1.isEmpty()) {
			throw new UserNotFoundException("Can't Find User;");// 自定義例外
		}
		return op1.get();
	}
	//會員忘記密碼或更換信箱時查詢身分用
	public CustomerBean getBySecretkey(String secretkey) {
		Optional<CustomerBean> op1 = cusReps.getBySecretkey(secretkey);
		if(op1.isEmpty()) {
			throw new UserNotFoundException("Can't Find Secretkey;");// 自定義例外
		}
		return op1.get();
	}
	
	

}
