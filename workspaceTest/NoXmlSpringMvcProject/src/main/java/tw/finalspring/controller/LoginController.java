package tw.finalspring.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tw.finalspring.model.CustomerBean;
import tw.finalspring.service.CustomerService;

@Controller
@SessionAttributes(names = { ("realName"),("username") }) //設為session層級
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
		String result = cusService.selectUsername(cusBean);// 回傳值為pass或fail
		if ("pass".equals(result)) {
			String realName = cusService.selectUsernameLogin(cusBean);//抓取使用者名稱
			System.out.println(realName);
			m.addAttribute("realName", realName);
			return result;
		}

		// 回傳給html端
		return result;

	}

}
