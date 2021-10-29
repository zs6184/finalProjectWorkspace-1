package tw.springbootfinal.users.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
@RequestMapping(path = "/Users")
@SessionAttributes(value = { "cusId","realName" })
public class CustomerCenterController {

	@Autowired
	private CustomerService cusService;

	//進入會員中心時取得基本資料及圖片
	@GetMapping("/SelectCustomer.controller")
	public String processSelectCustomer(HttpSession session, Model m, HttpServletRequest request) {
		// 取得登入時session層級的username
		Object attr = session.getAttribute("username");
		String str = attr.toString();
		System.out.println(str);
		CustomerBean cusBean = new CustomerBean();
		cusBean.setCusUsername(str);// 設定username到Bean
		// 利用username取得list結果集
		List<CustomerBean> cus = cusService.findByCustomerCenterUsername(cusBean);
		m.addAttribute("cus", cus);// 將結果設成屬性給jsp使用

		int cusId = 0;
		String imageName = null;
		byte[] image = {};
		// 取得資料庫資料
		for (CustomerBean customerBean : cus) {
			cusId = customerBean.getCusId();
			imageName = customerBean.getImageName();
			image = customerBean.getImage();
			System.out.println("imageName: "+imageName);
		}
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
		
		return "customerCenter";
	}

	// 會員中心資料更新
	@PostMapping("/UpdateCustomer.controller")
	@ResponseBody
	public String processUpdateCustomer(@RequestParam("image") MultipartFile mFile, @RequestParam("cusId") int cusId,
			@RequestParam("cusRealname") String realName, @RequestParam("aka") String aka,
			@RequestParam("gender") String gender, @RequestParam("birthdate") String birthdate,
			@RequestParam("phoneNumber") String phone, @RequestParam("email") String email,
			@RequestParam("address") String address, Model m, HttpServletRequest request)
			throws IllegalStateException, IOException {

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
		
		m.addAttribute("realName",realName);
		
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
			cusService.saveFile(cusId, cusBean, saveFilePath);//連圖片一起存
		}else {
			cusService.save(cusBean);//更新個人資料
		}
		
		saveFile.delete();// 刪除存在硬碟的檔案

		return "pass";
	}

	// 判斷電話是否重複
	@PostMapping("/CustomerCenterCheckPhone.controller")
	@ResponseBody
	public String processCustomerCenterCheckPhone(@RequestParam("phoneNumber") String phoneNumber,
			HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		CustomerBean cBean = new CustomerBean();

		// 設定到Bean
		cBean.setPhoneNumber(phoneNumber);

		// 判斷電話是否存在
		String resultPhone = cusService.findByPhoneNumber(cBean);

		// 察看結果
		System.out.println(resultPhone);

		return resultPhone;
	}

	// 判斷Email是否重複
	@PostMapping("/CustomerCenterCheckEmail.controller")
	@ResponseBody
	public String processCustomerCenterCheckEmail(@RequestParam("email") String email, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		CustomerBean cBean = new CustomerBean();

		// 設定到Bean
		cBean.setEmail(email);

		// 判斷email是否存在
		String resultEmail = cusService.findByEmail(cBean);

		// 察看結果
		System.out.println(resultEmail);

		return resultEmail;
	}
	
	//進入變更密碼時取得圖片
	@GetMapping("/CheckPassword.Controller")
	public String processSelectUserImage(@SessionAttribute("username") String username, Model m, HttpServletRequest request) {
		
		String selectUserImage = cusService.SelectUserImage(username, m, request);
		System.out.println(selectUserImage);
		return "checkPassword";
	}
	
	//變更密碼前的密碼確認
	@PostMapping(path = "/CheckPasswordBT.Controller")
	@ResponseBody
	public String processCheckPassword(@RequestParam("password") String password,HttpSession session) {
		Object attr = session.getAttribute("username");
		String str = attr.toString();
		System.out.println(str);
		
		//取得會員資料
		CustomerBean cusBean = cusService.getByCusUsername(str);
		String cusPassword = cusBean.getCusPassword();
		boolean result = new BCryptPasswordEncoder().matches(password, cusPassword);//加密後的密碼比對
		System.out.println(result);
		if(result) {
			return "pass";
		}
		return "fail";
	}
	
	//會員中心密碼更新
	@PostMapping(path = "/UpdatePassword.Controller")
	@ResponseBody
	public String processUpdatePassword(@RequestParam("newPassword") String password,HttpSession session) {
		
		Object attr = session.getAttribute("username");
		String str = attr.toString();
		System.out.println(str);
		
		System.out.println("password: "+password);
		//取得會員資料
		CustomerBean cusBean = cusService.getByCusUsername(str);
		int cusId = cusBean.getCusId();
		
		cusBean.setCusPassword(password);
//		//進行加密
		String encodePwd = new BCryptPasswordEncoder().encode(cusBean.getCusPassword());
		System.out.println("encodePwd: "+encodePwd);
		cusBean.setCusPassword(encodePwd);//存回bean
		cusService.save(cusBean);//更新密碼
		return "success";
	}
	
	//進入變更信箱時取得圖片
	@GetMapping("/EmailCheckPassword.Controller")
	public String processEmailSelectUserImage(@SessionAttribute("username") String username, Model m, HttpServletRequest request) {
		
		String selectUserImage = cusService.SelectUserImage(username, m, request);
		System.out.println(selectUserImage);
		return "emailCheckPassword";
	}

}
