package tw.finalspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.finalspring.model.CustomerBean;
import tw.finalspring.service.CustomerService;

@Controller
public class CustomersController {

	@Autowired
	private CustomerService cusService;

	@RequestMapping(path = "/Customer.Controller")
	public String processCustomer() {
		return "Customer";
	}

	@RequestMapping(path = "/SelectCustomerAll.Controller", method = RequestMethod.GET)
	public String processSelectCustomerAll(Model m) {
		List<CustomerBean> selectAll = cusService.selectAll();//搜尋所有會員資料
		//將整個陣列傳到jsp的forEach
		m.addAttribute("cus", selectAll);
		return "Customer";
	}
	
	@RequestMapping(path = "/SelectCustomerById.Controller")
	@ResponseBody
	public CustomerBean processSelectCustomerById(@RequestParam("id") int id) {
		CustomerBean selectOne = cusService.select(id);
		
		System.out.println(selectOne);//查看會員資料
		return selectOne;
	}
	
	@RequestMapping(path = "/DeleteCustomerById.Controller")
	@ResponseBody
	public boolean processDeleteCustomerById(@RequestParam("id") int id) {
		boolean deletOne = cusService.deletOne(id);
		System.out.println(deletOne); //查看成功或失敗
		return deletOne;
	}
	
	@RequestMapping(path = "/UpdateCustomerById.Controller",method = RequestMethod.POST)
	@ResponseBody
	public String processUpdateCustomerById(@RequestParam("cusUpdateId") int id,
			@RequestParam("cusNameUpdate") String cusName,
			@RequestParam("phoneNumberUpdate") String phone,
			@RequestParam("userNameUpdate") String aka,
			@RequestParam("genderUpdate") String gender,
			@RequestParam("birthdateUpdate") String birthdate,
			@RequestParam("emailUpdate") String email,
			@RequestParam("addressUpdate") String address,
			@RequestParam("notesUpdate") String notes
			) {
		 CustomerBean cusBean = new CustomerBean();
		 cusBean.setCusRealname(cusName);
		 cusBean.setPhoneNumber(phone);
		 cusBean.setAka(aka);
		 cusBean.setGender(gender);
		 cusBean.setBirthdate(birthdate);
		 cusBean.setEmail(email);
		 cusBean.setAddress(address);
		 cusBean.setNote(notes);
		CustomerBean updateOne = cusService.updateOne(id,cusBean);
		
		System.out.println(notes);
		
		
//		System.out.println(updateOne.getCusRealname()); //查看成功或失敗
		return "pass";
	}
}
