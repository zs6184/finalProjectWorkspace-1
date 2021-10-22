package iii.boothomework.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import iii.boothomework.model.CustomerService;
import iii.boothomework.model.Customers;

@Controller
@RequestMapping("/users")
@SessionAttributes(names = { "realName","theId" }) // 設為session層級
public class CustomerController {
	
	int status=0;
	
	@Autowired
	private CustomerService cService;
//------------------------------------------------------------------
	// 登入檢查帳密
	@PostMapping("/checkloginaccount.controller")
	public String processCheckAccount(Customers user, Model m) {
		status=0;
		String username = user.getUsername();
		String password = user.getPassword();
		// 未填寫完整表單情況
		if (username == "" || password == "") {
			return "loginEmpty";
		}
		// 確認完整填寫-開始抓資料
		Optional<Customers> check = cService.getByUsername(username);
		//確認是否有結果(帳號存在與否)
		if (check.isPresent()) {
			Customers temp = check.get();
			//比較密碼-正確
			if((temp.getPassword()).equals(password)) {
				String realName = temp.getRealname();
				int theId = temp.getId();
				m.addAttribute("realName", realName);
				m.addAttribute("theId",theId);
				return "loginIndex";
			}
			//比較密碼-錯誤
			return "loginFail";
		}
		//帳號不存在
		return "loginFail";
	}
//--------------------------------------------------------------------	
	//進入個人頁面使用Id獲取個資
	@GetMapping("/personnelData.controller")
	public String processTheCus(@SessionAttribute("theId") int id,Model m) {
		Customers theCus = cService.findById(id);
		m.addAttribute("theCus",theCus);
		
		if(status==0) m.addAttribute("msg","");
		if(status==1) m.addAttribute("msg","尚有未填欄位");
		if(status==2) m.addAttribute("msg","已完成修改");
		if(status==3) m.addAttribute("msg","此帳號已被使用");
		
		return "personnelData";
	}
//------------------------------------------------------------------	
	// 修改會員資料
	@PostMapping("/updateData.controller")
	public String processUpdateUsers(Customers user,Model m) {
		String username = user.getUsername();
		String password = user.getPassword();
		String realname = user.getRealname();
		System.out.println(username+"-"+password+"-"+realname);
		//判斷是否填寫完整&帳號是否可用
		if(username==""|| password==""|| realname=="") {
			status=1;
			return "redirect:/users/personnelData.controller";
		}else {
			Optional<Customers> check = cService.getByUsername(username);
			if(check.isEmpty()) {
				cService.updateUsers(user);
				status=2;
				return "redirect:/users/personnelData.controller";
			}else {
				status=3;
				return "redirect:/users/personnelData.controller";
			}
		}
	}
//------------------------------------------------------------------	
	//刪除會員資料
	@PostMapping("/deleteData.controller")
	public String processDeleteUsers(@SessionAttribute("theId") int id) {
		cService.deleteById(id);
		return "redirect:/logoutIndex.Controller";
	}
}
