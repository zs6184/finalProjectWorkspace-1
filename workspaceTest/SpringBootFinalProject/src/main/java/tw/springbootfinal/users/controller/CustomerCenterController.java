package tw.springbootfinal.users.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import tw.springbootfinal.mail.MailService;
import tw.springbootfinal.users.model.AuthenticationProvider;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;
import tw.springbootfinal.users.model.EmployeeBean;
import tw.springbootfinal.users.model.EmployeeService;

@Controller
@RequestMapping(path = "/Users")
@SessionAttributes(value = { "cusId", "realName", "role", "username", "provider" })
public class CustomerCenterController {

	@Autowired
	private CustomerService cusService;

	@Autowired
	private EmployeeService empService;

	@Autowired
	private MailService mService;

	// 進入會員中心時取得基本資料及圖片 Principal p
	@GetMapping("/SelectCustomer.controller")
	public String processSelectCustomer(@SessionAttribute("username") String username,
			@SessionAttribute("role") String role, HttpSession session, Model m, HttpServletRequest request) {

		System.out.println("查看role: " + role);
		if ("MEMBER".equals(role)) { // 如果是會員權限
			CustomerBean cusBean = cusService.getByCusUsername(username);
			// m.addAttribute("cus", cus);// 將結果設成屬性給jsp使用
			int cusId = cusBean.getCusId();
			String realName = cusBean.getCusRealname();
			String aka = cusBean.getAka();
			String gender = cusBean.getGender();
			String birthdate = cusBean.getBirthdate();
			String phoneNumber = cusBean.getPhoneNumber();
			String email = cusBean.getEmail();
			String address = cusBean.getAddress();
			String imageName = cusBean.getImageName();
			byte[] image = cusBean.getImage();
			AuthenticationProvider authProvide = cusBean.getAuthProvide();

			String provider = null;
			if (authProvide != null) { // 如果不是空的就用toString方法轉成字串
				provider = authProvide.toString();
			} else {
				System.out.println("authProvide:為null");
			}

			System.out.println("imageName: " + imageName);
			// 如果會員沒有上傳過圖片就使用預設圖片
			if (image == null) {
				m.addAttribute("imageName", "husky.jpg");
			} else {
				// 抓到專案路徑加上暫存資料夾名稱
				String path = request.getSession().getServletContext().getRealPath("/") + "downloadTempDir\\";
				System.out.println(path);
				cusService.imageDownload(image, cusId, imageName, path);

				m.addAttribute("imageName", imageName);
				m.addAttribute("cusId", cusId);
			}
			m.addAttribute("id", cusId);
			m.addAttribute("username", username);
			m.addAttribute("realName", realName);
			m.addAttribute("aka", aka);
			m.addAttribute("gender", gender);
			m.addAttribute("birthdate", birthdate);
			m.addAttribute("phoneNumber", phoneNumber);
			m.addAttribute("email", email);
			m.addAttribute("address", address);
			m.addAttribute("role", role);
			m.addAttribute("provider", provider);

		} else {// 如果是員工或管理者權限
			EmployeeBean empBean = empService.getByEmpUsername(username);
			int empId = empBean.getEmpId();
			String realName = empBean.getEmpRealname();
			String aka = empBean.getAka();
			String gender = empBean.getGender();
			String birthdate = empBean.getBirthdate();
			String phoneNumber = empBean.getPhoneNumber();
			String email = empBean.getEmail();
			String address = empBean.getAddress();
			String imageName = empBean.getImageName();
			byte[] image = empBean.getImage();

			System.out.println("imageName: " + imageName);
			// 如果會員沒有上傳過圖片就使用預設圖片
			if (image == null) {
				m.addAttribute("imageName", "husky.jpg");
			} else {
				// 抓到專案路徑加上暫存資料夾名稱
				String path = request.getSession().getServletContext().getRealPath("/") + "downloadTempDir\\";
				System.out.println(path);
				empService.imageDownload(image, empId, imageName, path);

				m.addAttribute("imageName", imageName);
				m.addAttribute("cusId", empId);
			}
			m.addAttribute("id", empId);
			m.addAttribute("username", username);
			m.addAttribute("realName", realName);
			m.addAttribute("aka", aka);
			m.addAttribute("gender", gender);
			m.addAttribute("birthdate", birthdate);
			m.addAttribute("phoneNumber", phoneNumber);
			m.addAttribute("email", email);
			m.addAttribute("address", address);
			m.addAttribute("role", role);
		}
		return "customerCenter";
	}

	// 會員中心資料更新
	@PostMapping("/UpdateCustomer.controller")
	@ResponseBody
	public String processUpdateCustomer(@RequestParam("image") MultipartFile mFile, @RequestParam("cusId") int cusId,
			@RequestParam("cusRealname") String realName, @RequestParam("aka") String aka,
			@RequestParam("gender") String gender, @RequestParam("birthdate") String birthdate,
			@RequestParam("phoneNumber") String phone, @RequestParam("email") String email,
			@RequestParam("address") String address, @SessionAttribute("role") String role, Model m,
			HttpServletRequest request) throws IllegalStateException, IOException {

		if ("MEMBER".equals(role)) {
			// 取得指定會員原始資料
			CustomerBean cusBean = cusService.findById(cusId);
			// 將要更新的資料放到bean覆蓋原始資料
			cusBean.setCusId(cusId);
			cusBean.setCusRealname(realName);
			cusBean.setAka(aka);
			cusBean.setGender(gender);
			cusBean.setBirthdate(birthdate);
			cusBean.setPhoneNumber(phone);
			cusBean.setEmail(email);
			cusBean.setAddress(address);

			m.addAttribute("realName", realName);

			// 將基本資料寫進資料庫
			// cusService.save(cusBean);
			System.out.println(cusId);
			String filename = mFile.getOriginalFilename();// 取得圖檔原始名稱
			String cusFilename = cusId + "_" + filename;

			// 取得專案路徑並加上要放圖片的資料夾名稱
			String saveFileDir = request.getSession().getServletContext().getRealPath("/") + "uploadTempDir\\";

			File file = new File(saveFileDir);// 放入路徑
			file.mkdirs();// 建立資料夾

			String saveFilePath = saveFileDir + cusFilename;// 路徑+檔名

			File saveFile = new File(saveFilePath);
			mFile.transferTo(saveFile);// 存到硬碟

			System.out.println(cusFilename);
			// 如果檔案存在，就將資料夾裡的圖片存到資料庫
			if (filename != null && filename.length() != 0) {
				cusBean.setImageName(cusFilename);// 將檔名存到Bean
				cusService.saveFile(cusId, cusBean, saveFilePath);// 連圖片一起存
			} else {
				cusService.save(cusBean);// 更新個人資料
			}

			saveFile.delete();// 刪除存在硬碟的檔案
		} else {
			// 取得指定會員原始資料
			EmployeeBean empBean = empService.findById(cusId);
			// 將要更新的資料放到bean覆蓋原始資料
			empBean.setEmpId(cusId);
			empBean.setEmpRealname(realName);
			empBean.setAka(aka);
			empBean.setGender(gender);
			empBean.setBirthdate(birthdate);
			empBean.setPhoneNumber(phone);
			empBean.setEmail(email);
			empBean.setAddress(address);

			m.addAttribute("realName", realName);
			// 將基本資料寫進資料庫
			// cusService.save(cusBean);
			System.out.println(cusId);
			String filename = mFile.getOriginalFilename();// 取得圖檔原始名稱
			String cusFilename = cusId + "_" + filename;

			// 取得專案路徑並加上要放圖片的資料夾名稱
			String saveFileDir = request.getSession().getServletContext().getRealPath("/") + "uploadTempDir\\";

			File file = new File(saveFileDir);// 放入路徑
			file.mkdirs();// 建立資料夾

			String saveFilePath = saveFileDir + cusFilename;// 路徑+檔名

			File saveFile = new File(saveFilePath);
			mFile.transferTo(saveFile);// 存到硬碟

			System.out.println(cusFilename);
			// 如果檔案存在，就將資料夾裡的圖片存到資料庫
			if (filename != null && filename.length() != 0) {
				empBean.setImageName(cusFilename);// 將檔名存到Bean
				empService.saveFile(cusId, empBean, saveFilePath);// 連圖片一起存
			} else {
				empService.save(empBean);// 更新個人資料
			}

			saveFile.delete();// 刪除存在硬碟的檔案
		}

		return "pass";
	}

	// 判斷電話是否重複
	@PostMapping("/CustomerCenterCheckPhone.controller")
	@ResponseBody
	public String processCustomerCenterCheckPhone(@SessionAttribute("role") String role,
			@RequestParam("phoneNumber") String phoneNumber, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		if ("MEMBER".equals(role)) {
			CustomerBean cBean = new CustomerBean();
			// 設定到Bean
			cBean.setPhoneNumber(phoneNumber);
			// 判斷電話是否存在
			String resultPhone = cusService.findByPhoneNumber(cBean);
			// 察看結果
			System.out.println(resultPhone);
			return resultPhone;
		} else {
			EmployeeBean empBean = new EmployeeBean();
			// 設定到Bean
			empBean.setPhoneNumber(phoneNumber);
			// 判斷電話是否存在
			String resultPhone = empService.findByPhoneNumber(empBean);
			// 察看結果
			System.out.println(resultPhone);
			return resultPhone;
		}

	}

	// 判斷Email是否重複
	@PostMapping("/CustomerCenterCheckEmail.controller")
	@ResponseBody
	public String processCustomerCenterCheckEmail(@SessionAttribute("role") String role,
			@RequestParam("email") String email, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		if ("MEMBER".equals(role)) {
			CustomerBean cBean = new CustomerBean();
			// 設定到Bean
			cBean.setEmail(email);
			// 判斷email是否存在
			String resultEmail = cusService.findByEmail(cBean);
			// 察看結果
			System.out.println(resultEmail);

			return resultEmail;
		} else {
			EmployeeBean empBean = new EmployeeBean();
			// 設定到Bean
			empBean.setEmail(email);
			// 判斷email是否存在
			String resultEmail = empService.findByEmail(empBean);
			// 察看結果
			System.out.println(resultEmail);

			return resultEmail;
		}
	}

	// 進入變更密碼時取得圖片
	@GetMapping("/CheckPassword.Controller")
	public String processSelectUserImage(@SessionAttribute("username") String username,
			@SessionAttribute("role") String role, Model m, HttpServletRequest request) {
		if ("MEMBER".equals(role)) {
			String selectUserImage = cusService.SelectUserImage(username, m, request);
			System.out.println(selectUserImage);
		} else {
			String selectUserImage = empService.SelectUserImage(username, m, request);
			System.out.println(selectUserImage);
		}
		return "checkPassword";
	}

	// 變更密碼前的密碼確認
	@PostMapping(path = "/CheckPasswordBT.Controller")
	@ResponseBody
	public String processCheckPassword(@SessionAttribute("username") String username,
			@SessionAttribute("role") String role, @RequestParam("password") String password) {

		boolean result;
		String originPwd;

		if ("MEMBER".equals(role)) {// 取得會員資料
			CustomerBean cusBean = cusService.getByCusUsername(username);
			originPwd = cusBean.getCusPassword();
		} else {// 取得員工資料

			EmployeeBean empBean = empService.getByEmpUsername(username);
			originPwd = empBean.getEmpPassword();
		}

		result = new BCryptPasswordEncoder().matches(password, originPwd);// 加密後的密碼比對
		System.out.println(result);

		if (result) {
			return "pass";
		}
		return "fail";

	}

	// 會員中心密碼更新
	@PostMapping(path = "/UpdatePassword.Controller")
	@ResponseBody
	public String processUpdatePassword(@SessionAttribute("username") String username,
			@SessionAttribute("role") String role, @RequestParam("newPassword") String password, HttpSession session) {

		System.out.println("password: " + password);

		if ("MEMBER".equals(role)) {// 取得會員資料
			CustomerBean cusBean = cusService.getByCusUsername(username);

			cusBean.setCusPassword(password);
			// 進行加密
			String encodePwd = new BCryptPasswordEncoder().encode(cusBean.getCusPassword());
			System.out.println("encodePwd: " + encodePwd);
			cusBean.setCusPassword(encodePwd);// 存回bean
			cusService.save(cusBean);// 更新密碼
		} else {// 取得員工資料
			EmployeeBean empBean = empService.getByEmpUsername(username);

			empBean.setEmpPassword(password);
			// 進行加密
			String encodePwd = new BCryptPasswordEncoder().encode(empBean.getEmpPassword());
			System.out.println("encodePwd: " + encodePwd);
			empBean.setEmpPassword(encodePwd);// 存回bean
			empService.save(empBean);// 更新密碼
		}

		return "success";
	}

	// 進入變更信箱時取得圖片
	@GetMapping("/EmailCheckPassword.Controller")
	public String processEmailSelectUserImage(@SessionAttribute("username") String username,
			@SessionAttribute("role") String role, Model m, HttpServletRequest request) {
		if ("MEMBER".equals(role)) {
			String selectUserImage = cusService.SelectUserImage(username, m, request);
			System.out.println(selectUserImage);
		} else {
			String selectUserImage = empService.SelectUserImage(username, m, request);
			System.out.println(selectUserImage);
		}
		return "emailCheckPassword";
	}

	// 變更信箱寄送email
	@PostMapping("/ChangeEmailSendMail.Controller")
	@ResponseBody
	public String processdChangeEmailSendMail(@SessionAttribute("role") String role,
			@SessionAttribute("username") String username, @RequestParam("email") String email, Model m,
			HttpServletRequest request) throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {

		System.out.println("email名稱: " + email);

		String secretkey = null;
		String realname = null;

		// 抓取當下時間參數
		Date date = new Date();
		long time = date.getTime();
		String nowDate = String.format("%tY-%<tm-%<td %<tH:%<tM:%<tS", time);
		System.out.println("nowDate: " + nowDate);

		if ("MEMBER".equals(role)) {// 取得會員資料
			CustomerBean cusBean = cusService.getByCusUsername(username);
			realname = cusBean.getCusRealname();

			// 將使用者名稱跟時間做字串串接
			String secret = username + nowDate;
			System.out.println("secretkey: " + secret);
			System.out.println("=========================加密==========================");

			// 加密
			secretkey = new BCryptPasswordEncoder().encode(secret);
			System.out.println("加密後密鑰" + secretkey);

			// 將密鑰存到資料庫
			cusBean.setSecretkey(secretkey);
			cusBean.setTempEmail(email);
			cusService.save(cusBean);

		} else {// 取得員工資料
			EmployeeBean empBean = empService.getByEmpUsername(username);
			realname = empBean.getEmpRealname();

			// 將使用者名稱跟時間做字串串接
			String secret = username + nowDate;
			System.out.println("secretkey: " + secret);
			System.out.println("=========================加密==========================");

			// 加密
			secretkey = new BCryptPasswordEncoder().encode(secret);
			System.out.println("加密後密鑰" + secretkey);

			// 將密鑰存到資料庫
			empBean.setSecretkey(secretkey);
			empBean.setTempEmail(email);
			empService.save(empBean);
		}

		// 季送信件
		String url = "http://localhost:8080/Users/SaveCheckEmail.Controller?secretkey=" + secretkey;
		m.addAttribute("url", url);
		Map<String, Object> model = new HashMap<String, Object>();// 放置信件所需的參數
		model.put("userName", realname);
		model.put("url", url);

		String templateNmae = "mailChangeMarker.html"; // 使用的信件樣式模板
		String head = "通知:浪跡變更電子郵件地址驗證通知。"; // 信件主旨
		boolean sendMail = mService.sendMail(request, email, model, templateNmae, head);
		System.out.println(sendMail);
		return "send Success";
	}

	// 儲存新的Email
	@GetMapping(path = "/SaveCheckEmail.Controller")
	public RedirectView processCheckUsername(@SessionAttribute("role") String role,
			@RequestParam("secretkey") String secretkey, Model m) {
		
		String username;
		
		if ("MEMBER".equals(role)) {
			CustomerBean cusBean = cusService.getBySecretkey(secretkey);
			username = cusBean.getCusUsername();
			cusBean.setEmail(cusBean.getTempEmail()); // 將暫存的新電子郵件地址更新
			cusBean.setTempEmail("");
			cusService.save(cusBean);
			
		}else {
			
			EmployeeBean empBean = empService.getBySecretkey(secretkey);
			username = empBean.getEmpUsername();
			empBean.setEmail(empBean.getTempEmail()); // 將暫存的新電子郵件地址更新
			empBean.setTempEmail("");
			empService.save(empBean);
		}
		System.out.println("username:" + username);
		m.addAttribute("username", username);

		String url = "/Users/SelectCustomer.controller#information";
		// RedirectView:直接使用網址轉網頁
		return new RedirectView(url);
	}

}
