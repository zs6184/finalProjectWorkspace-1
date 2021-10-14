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
@SessionAttributes(names = {"realName"}) //設為session層級

public class LoginController {

	int cusId;
	int status=2;
	@Autowired
	private CustomerService cusService;
	
	//登入畫面
	@RequestMapping(path = "/login.Controller", method = RequestMethod.GET)
	public String processLogin() {
		status=2;
		return "login";
	}

	//登入後首頁
	@RequestMapping(path = "/loginIndex.Controller", method = RequestMethod.GET)
	public String processLoginIndex() {
		status=2;
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
		status=2;
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
			CustomerBean theCus = cusService.selectUsernameLogin(cusBean);//抓取使用者名稱
			String realName =theCus.getCusRealname();
			cusId = theCus.getCusId();
			System.out.println("cusId="+cusId);
			
			m.addAttribute("realName", realName); 
			
			return "loginIndex";
		}
		return "loginFail";

	}
	
	//進入個人資料頁
	@RequestMapping(path = "/personnelData.controller",method = RequestMethod.GET)
	public String processGetCusData(Model m) {
		CustomerBean theCus = cusService.selectById(cusId);
		
		m.addAttribute("theCus",theCus);
		if(status==2) m.addAttribute("msg", "");
		if(status==1) m.addAttribute("msg", "資料修改成功");
		if(status==0) m.addAttribute("msg", "此帳號已有人使用");
		
		return "personnelData";
	}
	
	//修改個人資料
	@RequestMapping(path = "/updateData.controller",method = RequestMethod.POST)
	public String updatePersonnelData(@RequestParam("cusRealname")String cusRealname,@RequestParam("cusUsername")String cusUsername,@RequestParam("cusPassword")String cusPassword,Model m) {
		CustomerBean temp = new CustomerBean(cusId,cusUsername,cusPassword,cusRealname);
		status=2;
		// 判斷帳號是否存在
		boolean resultUsername = cusService.selectCreateCusUsername(temp);
		// 如果資料不存在就修改會員資料
		if (resultUsername) {
			cusService.updateOne(cusId,temp);
			System.out.println("資料修改成功");
			status=1;
			return "redirect:/personnelData.controller";
		} else {
			System.out.println("此帳號已有人使用");
			status=0;
			return "redirect:/personnelData.controller";
		}
	}
	
	//刪除個人資料
	@RequestMapping(path = "/deleteData.controller",method = RequestMethod.POST)
	public String deletePersonnelData() {
		System.out.println("Delete cusId="+cusId);
		cusService.deleteOne(cusId);
		
		return "redirect:/login.Controller";
	}

}
