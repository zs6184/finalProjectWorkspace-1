package iii.boothomework.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import iii.boothomework.model.CustomerService;
import iii.boothomework.model.Customers;

@Controller

public class CreateAccountController {

	@Autowired
	private CustomerService cService;
//------------------------------------------------------------------
	
	// 新增會員資料
	@PostMapping("/CreateCusAccount.controller")
	public String processCreateUsers(Customers user) {
		String username = user.getUsername();
		String password = user.getPassword();
		String realname = user.getRealname();
		//判斷是否填寫完整&判斷帳號是否已被使用
		if(username==""|| password==""|| realname=="") {
			return "createCusAccountEmpty";
		}else {
			Optional<Customers> check = cService.getByUsername(username);
			if(check.isEmpty()) {
				//建立成功回登入頁
				cService.createUsers(user);
				return "login";
			}else {
				//已被使用建立失敗
				return "createCusAccountFail";
			}
		}
	}

}
