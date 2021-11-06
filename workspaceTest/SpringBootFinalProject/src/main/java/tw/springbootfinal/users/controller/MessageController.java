package tw.springbootfinal.users.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
@RequestMapping("/Backstage")
public class MessageController {

	@Autowired
	private CustomerService cusService;
	
	//即時客服首頁
	@GetMapping("/MessagePage.Controller")
	public String processMessagePageAction(@SessionAttribute("username") String username, Model m, HttpServletRequest request) {
		System.out.println("username: "+username);
		CustomerBean cusBean = cusService.getByCusUsername(username);
		String imageName = cusBean.getImageName();
		cusService.selectImage(username, request);
		m.addAttribute("imageName",imageName);
		return "message";
	}
	
}
