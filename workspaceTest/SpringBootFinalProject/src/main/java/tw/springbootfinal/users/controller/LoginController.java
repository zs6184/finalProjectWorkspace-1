package tw.springbootfinal.users.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;
import tw.springbootfinal.users.model.Recaptcha;
import tw.springbootfinal.users.model.RecaptchaService;

@Controller
@SessionAttributes(names = { "realName","username","role" }) //設為session層級
public class LoginController {

	@Autowired
	private CustomerService cusService;
	
	@Autowired
	private RecaptchaService recaptchaService;

	//登入後取得realName
	@GetMapping("/Users/loginIndex.Controller")
	public String processloginIndexMainPage(Principal p, Model m) { 
		String username = p.getName();//Principal可以用來取得使用者名稱
		System.out.println("username"+username);
		CustomerBean cusBean = cusService.getByCusUsername(username);//透過使用者名稱搜尋資料
		String realName = cusBean.getCusRealname();//取得真實姓名
		String role = cusBean.getRole();
		m.addAttribute("username",username);//設為session層級的變數給jsp使用
		m.addAttribute("realName",realName);//設為session層級的變數給jsp使用
		m.addAttribute("role",role);
		return "loginIndex";
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
	
	//處理recaptcha請求並發送給google驗證，並回應給前端
	@PostMapping(path = "/reCAPTCHA.controller")
	@ResponseBody
	public Recaptcha token(@RequestParam("token") String token) {
		Recaptcha reCAPTCHA = recaptchaService.ReCAPTCHA(token);
		
		System.out.println("Challenge_ts: "+reCAPTCHA.getChallenge_ts());
		System.out.println("Action: "+reCAPTCHA.getAction());
		System.out.println("Hostname: "+reCAPTCHA.getHostname());
		System.out.println("Score: "+reCAPTCHA.getScore());
		
		return reCAPTCHA;
				
	}
}
