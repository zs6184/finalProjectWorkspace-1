package tw.springbootfinal.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
@RequestMapping(path = "/Backstage")
public class CustomersController {

	@Autowired
	private CustomerService cusService;

	@RequestMapping(path = "/Customer.Controller")
	public String processCustomer() {
		return "Customer";
	}

	// 查詢全部會員
	@RequestMapping(path = "/SelectCustomerAll.Controller", method = RequestMethod.GET)
	public String processSelectCustomerAll(Model m) {
		List<CustomerBean> selectAll = cusService.findAll();// 搜尋所有會員資料
		// 將整個陣列傳到jsp的forEach
		m.addAttribute("cus", selectAll);
		return "Customer";
	}

	// 用Id查詢指定會員
	@RequestMapping(path = "/SelectCustomerById.Controller")
	@ResponseBody
	public CustomerBean processSelectCustomerById(@RequestParam("id") int id) {
		CustomerBean selectOne = cusService.findById(id);
		System.out.println(selectOne);// 查看會員資料
		return selectOne;
	}

	// 刪除指定會員
	@RequestMapping(path = "/DeleteCustomerById.Controller")
	@ResponseBody
	public String processDeleteCustomerById(@RequestParam("id") int id) {
		cusService.deleteById(id);
		return "true";
	}

	// 更新指定會員
	@RequestMapping(path = "/UpdateCustomerById.Controller", method = RequestMethod.POST)
	@ResponseBody
	public String processUpdateCustomerById(@RequestParam("cusUpdateId") int id,
			@RequestParam("cusNameUpdate") String cusName, @RequestParam("phoneNumberUpdate") String phone,
			@RequestParam("userNameUpdate") String aka, @RequestParam("genderUpdate") String gender,
			@RequestParam("birthdateUpdate") String birthdate, @RequestParam("emailUpdate") String email,
			@RequestParam("addressUpdate") String address, @RequestParam("notesUpdate") String notes) {

		// 取得指定會員原始資料
		CustomerBean cusBean = cusService.findById(id);
		// 將要更新的資料放到bean覆蓋原始資料
		cusBean.setCusRealname(cusName);
		cusBean.setPhoneNumber(phone);
		cusBean.setAka(aka);
		cusBean.setGender(gender);
		cusBean.setBirthdate(birthdate);
		cusBean.setEmail(email);
		cusBean.setAddress(address);
		cusBean.setNote(notes);
		// 更新
		cusService.save(cusBean);

		System.out.println(notes);
		return "pass";
	}
}
