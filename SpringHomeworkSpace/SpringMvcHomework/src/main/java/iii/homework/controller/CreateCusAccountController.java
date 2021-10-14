package iii.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import iii.homework.model.CustomerBean;
import iii.homework.service.CustomerService;

@Controller
public class CreateCusAccountController {

	@Autowired
	private CustomerService cusService;

	// 建立帳號
	@RequestMapping(path = "/CreateCusAccount.controller", method = RequestMethod.POST)
	public String processCreateCusAccountAction(CustomerBean cBean) {

		String username = cBean.getCusUsername();
		String pwd = cBean.getCusPassword();
		String realNeam = cBean.getCusRealname();
		
		if (username == "" || pwd == "" || realNeam =="") {
			System.out.println("------------------");
			return "createCusAccountEmpty";
		}else {
			// 判斷帳號是否存在
			boolean resultUsername = cusService.selectCreateCusUsername(cBean);
			
			// 如果資料不存在就新建會員資料
			if (resultUsername) {
				// 新增資料
				cusService.insert(cBean);
				System.out.println("會員建立成功");
				return "login";
			} else {
				System.out.println("資料已存在");
				return "createCusAccountFail";
			}
		}
	}
}
