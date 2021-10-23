package tw.springbootfinal.users.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;

import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;
import tw.springbootfinal.users.model.Recaptcha;

@Controller
@RequestMapping(path = "/Users")
@SessionAttributes(names = { "realName","username" }) //設為session層級
public class LoginController {

	@Autowired
	private CustomerService cusService;

	@RequestMapping(path = "/loginIndex.Controller", method = RequestMethod.GET)
	public String processloginIndexMainPage() {
		return "loginIndex";
	}

	@RequestMapping(path = "/logoutIndex.Controller", method = RequestMethod.GET)
	public String processLogoutMainPage(SessionStatus status) {
		status.setComplete();
		return "logout";
	}

	@RequestMapping(path = "/login.Controller", method = RequestMethod.GET)
	public String processLoginMainPage() {
		return "login";
	}

	@RequestMapping(path = "/checkloginaccount.controller", method = RequestMethod.POST)
	@ResponseBody
	public String processAction(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletResponse response, Model m) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");// CORS策略

		// 將帳號密碼放進Bean
		CustomerBean cusBean = new CustomerBean();
		cusBean.setCusUsername(username);
		cusBean.setCusPassword(password);

		m.addAttribute("username", username);

		// 使用service的方法查詢使用者資料，並判斷是否存在或正確
		String result = cusService.findByCusUsername(cusBean);// 回傳值為pass或fail
		if ("pass".equals(result)) {
			String realName = cusService.findByCusUsernameLogin(cusBean);//抓取使用者名稱
			System.out.println(realName);
			m.addAttribute("realName", realName);
			return result;
		}

		// 回傳給html端
		return result;
	}
	
	//reCAPTCHA未完成
	@RequestMapping(path = "/reCAPTCHA.controller",method = RequestMethod.POST)
	@ResponseBody
	public String processReCAPTCHAAction(@RequestBody String token) {
		System.out.println("進後端驗證密鑰");
		System.out.println("前端回傳的token"+token);
		//密鑰
		String secretKey = "6Lcnq94cAAAAAEhdIjBGvTDx9qFyH2hSU8_4BnIJ";
		Recaptcha recap = new Recaptcha();
		//發送http請求給google
//		String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + secretKey + "&response=" + token;
		String url = "https://www.google.com/recaptcha/api/siteverify";//google驗證網址
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("secret", secretKey);
		map.add("response", token);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		
		System.out.println("response: "+response);

		return "list";
	}
	
}
