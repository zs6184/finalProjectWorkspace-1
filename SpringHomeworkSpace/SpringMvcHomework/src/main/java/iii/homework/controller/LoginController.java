package iii.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import iii.homework.model.CustomerBean;
import iii.homework.service.CustomerService;

@Controller
@SessionAttributes(names = { ("realName") }) //設為session層級
public class LoginController {

	@Autowired
	private CustomerService cusService;
	
	//登入畫面
	@RequestMapping(path = "/login.Controller", method = RequestMethod.GET)
	public String processLogin() {
		return "login";
	}

	//登入後首頁
	@RequestMapping(path = "/loginIndex.Controller", method = RequestMethod.GET)
	public String processLoginIndex() {
		return "loginIndex";
	}

	//登出後首頁
	@RequestMapping(path = "/logoutIndex.Controller", method = RequestMethod.GET)
	public String processLogoutIndex(SessionStatus status) {
		status.setComplete();//取消session
		return "logout";
	}
	
	@RequestMapping(path = "/checkloginaccount.controller", method = RequestMethod.POST)
	public String processAction(@RequestParam("username") String username, 
			@RequestParam("password") String password, Model m){

		// 將帳號密碼放進Bean
		CustomerBean cusBean = new CustomerBean();
		cusBean.setCusUsername(username);
		cusBean.setCusPassword(password);

		m.addAttribute("username", username);

		if(username =="" || password =="") {
			return "loginEmpty";
		}
		// 使用service的方法查詢使用者資料，並判斷是否存在或正確
		boolean result = cusService.selectUsername(cusBean);
		if (result) {
			String realName = cusService.selectUsernameLogin(cusBean);//抓取使用者名稱
			m.addAttribute("realName", realName); 
			return "loginIndex";
		}
		return "loginFail";

	}

}
