package tw.springbootfinal.users.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import tw.springbootfinal.announcements.model.Announcements;
import tw.springbootfinal.announcements.model.AnnouncementsService;
import tw.springbootfinal.mail.MailService;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;
import tw.springbootfinal.users.model.Recaptcha;
import tw.springbootfinal.users.model.RecaptchaService;

@Controller
@SessionAttributes(names = { "realName", "username", "role" }) // 設為session層級
public class LoginController {

	@Autowired
	private CustomerService cusService;

	@Autowired
	private RecaptchaService recaptchaService;

	@Autowired
	private MailService mService;

	@Autowired
	private AnnouncementsService aService;
	
	@GetMapping("/")
	public String processGoogleLoginIndexPage(@SessionAttribute("username") String username,Model m) {
		CustomerBean cusBean = cusService.getByCusUsername(username);
		String realName = cusBean.getCusRealname();
		String role = cusBean.getRole();
		m.addAttribute("realName", realName);// 設為session層級的變數給jsp使用
		m.addAttribute("role", role);
		return "loginIndex";
	}
	
	// 登入後取得realName
	@GetMapping("/Users/loginIndex.Controller")
	public String processloginIndexMainPage(Principal p, Model m) throws UnsupportedEncodingException {
		String username = p.getName();// Principal可以用來取得使用者名稱
		System.out.println("username" + username);
		CustomerBean cusBean = cusService.getByCusUsername(username);// 透過使用者名稱搜尋資料
		String realName = cusBean.getCusRealname();// 取得真實姓名
		String role = cusBean.getRole();
		m.addAttribute("username", username);// 設為session層級的變數給jsp使用
		m.addAttribute("realName", realName);// 設為session層級的變數給jsp使用
		m.addAttribute("role", role);

		// 導入公告內容
		List<Announcements> arrAnnounce = aService.getAll();
		Map<Integer, String> baseStr = new HashMap<>();
		for (Announcements aAnn : arrAnnounce) {
			byte[] base64 = Base64.encodeBase64(aAnn.getPic());
			String base64Str = new String(base64, "UTF-8");
			baseStr.put(aAnn.getAnnounceID(), base64Str);

		}
		m.addAttribute("arrAnnounce", arrAnnounce);
		m.addAttribute("baseStr", baseStr);

		return "loginIndex";
	}

	// 忘記密碼判斷Email是否存在
	@PostMapping("/ForgetEmail.controller")
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

	// 忘記密碼寄送email
	@PostMapping("/ForgotPasswordSendMail")
	@ResponseBody
	public String processForgotPasswordSendMail(@RequestParam("email") String email, Model m,
			HttpServletRequest request) throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		// CustomerBean cusBean = cusService.getByEmail(email);
		List<CustomerBean> cus = cusService.findByEmail(email);
		if (cus.isEmpty()) {
			// return "test";
			return "pass";
		}
		String realname = null;
		String username = null;
		for (CustomerBean customerBean : cus) {
			realname = customerBean.getCusRealname();
			username = customerBean.getCusUsername();
		}
		CustomerBean cusBean = cusService.getByCusUsername(username);

		// 抓取當下時間參數
		Date date = new Date();
		long time = date.getTime();
		String nowDate = String.format("%tY-%<tm-%<td %<tH:%<tM:%<tS", time);
		System.out.println("nowDate: " + nowDate);

		// 將使用者名稱跟時間做字串串接
		String secret = username + nowDate;
		System.out.println("secretkey: " + secret);
		System.out.println("=========================加密==========================");

		// 加密
		String secretkey = new BCryptPasswordEncoder().encode(secret);
		System.out.println("加密後密鑰" + secretkey);

		// 將密鑰存到資料庫
		cusBean.setSecretkey(secretkey);
		cusService.save(cusBean);

		String url = "http://localhost:8080/CheckEmailUsername.Controller?secretkey=" + secretkey;
		m.addAttribute("url", url);
		Map<String, Object> model = new HashMap<String, Object>();// 放置信件所需的參數
		model.put("userName", realname);
		model.put("url", url);

		String templateNmae = "mailMarker.html"; // 使用的信件樣式模板
		String head = "通知:浪跡變更密碼驗證通知。"; // 信件主旨
		boolean sendMail = mService.sendMail(request, cusBean, model, templateNmae, head);
		System.out.println(sendMail);
//			return "forgotPasswordWait";
		return "fail";
	}

	@GetMapping(path = "/CheckEmailUsername.Controller")
	public String processCheckUsername(@RequestParam("secretkey") String secretkey, Model m) {
		CustomerBean cusBean = cusService.getBySecretkey(secretkey);
		String username = cusBean.getCusUsername();
		System.out.println("secretkey:" + secretkey);
		System.out.println("username:" + username);
		m.addAttribute("username", username);
		return "emailChangePassword";
	}

	// 忘記密碼的更新密碼
	@PostMapping(path = "/UpdateEmailPassword.Controller")
	@ResponseBody
	public String processUpdatePassword(@SessionAttribute("username") String username,
			@RequestParam("newPassword") String password, HttpSession session) {

		System.out.println("------------------------姓名-----------------------" + username);
		System.out.println("username:" + username);
		System.out.println("password: " + password);
		// 取得會員資料
		CustomerBean cusBean = cusService.getByCusUsername(username);

		cusBean.setCusPassword(password);
		cusBean.setSecretkey("");
//			//進行加密
		String encodePwd = new BCryptPasswordEncoder().encode(cusBean.getCusPassword());
		System.out.println("encodePwd: " + encodePwd);
		cusBean.setCusPassword(encodePwd);// 存回bean
		cusService.save(cusBean);// 更新密碼
		return "success";
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @throws UnsupportedEncodingException
	 * 
	 *                                      尚未登入前頁面公告載入
	 */
	@GetMapping("/logout.Controller")
	public String processLoadingPage2(Model m) throws UnsupportedEncodingException {
		List<Announcements> arrAnnounce = aService.getAll();
		Map<Integer, String> baseStr = new HashMap<>();
		for (Announcements aAnn : arrAnnounce) {
			byte[] base64 = Base64.encodeBase64(aAnn.getPic());
			String base64Str = new String(base64, "UTF-8");
			baseStr.put(aAnn.getAnnounceID(), base64Str);

		}
		m.addAttribute("arrAnnounce", arrAnnounce);
		m.addAttribute("baseStr", baseStr);

		return "logout";
	}

//	//判斷帳密是否存在
//	@RequestMapping(path = "/checkloginaccount.controller", method = RequestMethod.GET)
//	//@ResponseBody
//	public String processAction(@RequestParam("username") String username, @RequestParam("password") String password,
//			HttpServletResponse response, Model m) throws ServletException, IOException {
//		response.setHeader("Access-Control-Allow-Origin", "*");// CORS策略
//
//		// 將帳號密碼放進Bean
//		CustomerBean cusBean = new CustomerBean();
//		cusBean.setCusUsername(username);
//		cusBean.setCusPassword(password);
//
//		m.addAttribute("username", username);
//
//		// 使用service的方法查詢使用者資料，並判斷是否存在或正確
//		String result = cusService.findByCusUsername(cusBean);// 回傳值為pass或fail
////		if ("pass".equals(result)) {
//			String realName = cusService.findByCusUsernameLogin(cusBean);//抓取使用者名稱
//			System.out.println(realName);
//			m.addAttribute("realName", realName);
////			return result;
////		}
//
//		// 回傳給html端
//		return "loginIndex";
//	}
//	
//	public String processPrincipalQuery(Principal p) {
//		String name = p.getName();
//		return name;
//	}

	// 處理recaptcha請求並發送給google驗證，並回應給前端
	@PostMapping(path = "/reCAPTCHA.controller")
	@ResponseBody
	public Recaptcha token(@RequestParam("token") String token) {
		Recaptcha reCAPTCHA = recaptchaService.ReCAPTCHA(token);

		System.out.println("Challenge_ts: " + reCAPTCHA.getChallenge_ts());
		System.out.println("Action: " + reCAPTCHA.getAction());
		System.out.println("Hostname: " + reCAPTCHA.getHostname());
		System.out.println("Score: " + reCAPTCHA.getScore());

		return reCAPTCHA;

	}
}
