package iii.homework.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import iii.homework.model.CustomerBean;
import iii.homework.service.CustomerService;

@Controller
public class CreateCusAccountController {
	
	@Autowired
	private CustomerService cusService;
	
	//建立帳號
	@RequestMapping(path = "/CreateCusAccount.controller",method = RequestMethod.POST)
	@ResponseBody
	public String processCreateCusAccountAction(CustomerBean cBean ,
			HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		// 判斷帳號、電話、email是否存在
		String resultUsername = cusService.selectCreateCusUsername(cBean);
		String resultPhone = cusService.selectPhone(cBean);
		String resultEmail = cusService.selectEmail(cBean);

		// 察看結果
		System.out.println(resultUsername);
		System.out.println(resultPhone);
		System.out.println(resultEmail);
		System.out.println("--------");

		// 如果資料不存在就新建會員資料
		if (resultUsername.equals("pass")
				&& resultPhone.equals("pass")
				&& resultEmail.equals("pass")) {

			// 新增資料
			cusService.insert(cBean);
			System.out.println("會員建立成功");
			return "pass";
		} else {
			System.out.println("資料已存在");
			return "fail";
		}

	}
	
	//判斷電話是否重複
	@RequestMapping(path = "/CreateCusAccountCheckPhone.controller",method = RequestMethod.POST)
	@ResponseBody
	public String processCreateCusAccountCheckPhone(@RequestParam("phoneNumber") String phoneNumber, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		CustomerBean cBean = new CustomerBean();

		// 設定到Bean
		cBean.setPhoneNumber(phoneNumber);

		// 判斷電話是否存在
		String resultPhone = cusService.selectPhone(cBean);

		// 察看結果
		System.out.println(resultPhone);

		return resultPhone;
	}
	
	//判斷帳號是否重複
	@RequestMapping(path = "/CreateCusAccountCheckUsername.controller",method = RequestMethod.POST)
	@ResponseBody
	public String processCreateCusAccountCheckUsername(@RequestParam("cusUsername") String username, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		CustomerBean cBean = new CustomerBean();

		// 設定到Bean
		cBean.setCusUsername(username);

		// 判斷帳號是否存在
		String resultUsername = cusService.selectCreateCusUsername(cBean);

		// 察看結果
		System.out.println(resultUsername);

		return resultUsername;
	}
	
	//判斷Email是否重複
	@RequestMapping(path = "/CreateCusAccountCheckEmail.controller",method = RequestMethod.POST)
	@ResponseBody
	public String processCreateCusAccountCheckEmail(@RequestParam("email") String email, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		CustomerBean cBean = new CustomerBean();

		// 設定到Bean
		cBean.setEmail(email);

		// 判斷email是否存在
		String resultEmail = cusService.selectEmail(cBean);

		// 察看結果
		System.out.println(resultEmail);

		return resultEmail;
	}
	
	
	
	
	
	
	

}
