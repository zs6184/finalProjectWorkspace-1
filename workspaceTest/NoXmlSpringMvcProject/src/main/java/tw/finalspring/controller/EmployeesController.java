package tw.finalspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.finalspring.model.EmployeeBean;
import tw.finalspring.service.EmployeeService;

@Controller
public class EmployeesController {
	
	@Autowired
	private EmployeeService empService;

	@RequestMapping("/Employees.Controller")
	public String processEmployees() {
		return "employees";
	}
	
	//抓取所有資料更新頁面
	@RequestMapping("/EmployeesAll.Controller")
	public String processSelectEmployeesAll(Model m) {
		List<EmployeeBean> selectAll = empService.selectAll();
		m.addAttribute("emp", selectAll);
		return "employees";
	}
	
	//搜尋帳號最大值
	@RequestMapping("/CreateEmpUsername.Controller")
	@ResponseBody
	public String processCreateEmpUsername() {
		System.out.println("Controller");
		String empUsername = empService.selectMaxUsername();//搜尋帳號最大值(最新)
		System.out.println(empUsername);
		
		return empUsername;
	}
	
	//新建員工帳號
	@RequestMapping(path = "/CreateEmpAccount.Controller",method = RequestMethod.POST)
	@ResponseBody
	public String processCreateEmpAccount(EmployeeBean eBean) {
		EmployeeBean empBean = empService.insert(eBean);
		System.out.println(empBean);
		return "pass";
	}
	
	//搜尋指定員工資料
	@RequestMapping(path = "/SelectEmployeeById.Controller")
	@ResponseBody
	public EmployeeBean processSelectEmployeeById(@RequestParam("id") int id) {
		EmployeeBean selectOne = empService.select(id);
		return selectOne;
	}
	
	//刪除指定員工資料
	@RequestMapping(path = "/DeleteEmployeeById.Controller")
	@ResponseBody
	public boolean processDeleteEmployeeById(@RequestParam("id") int id){
		boolean deleteOne = empService.deleteOne(id);
		return deleteOne;
		
	}
	
	//更新員工資料
	@RequestMapping(path = "/UpdateEmployeeById.Controller",method = RequestMethod.POST)
	@ResponseBody
	public String processUpdateEmployeeById(@RequestParam("empUpdateId") int id,
			@RequestParam("empNameUpdate") String empName,
			@RequestParam("hiredateUpdate") String hiredate,
			@RequestParam("titleUpdate") String title,
			@RequestParam("genderUpdate") String gender,
			@RequestParam("phoneNumberUpdate") String phoneNumber,
			@RequestParam("birthdateUpdate") String birthdate,
			@RequestParam("addressUpdate") String address,
			@RequestParam("notesUpdate") String note) {
		System.out.println("----------------");
		System.out.println(title);
		EmployeeBean empBean = new EmployeeBean();
		empBean.setEmpRealname(empName);
		empBean.setHiredate(hiredate);
		empBean.setTitle(title);
		empBean.setGender(gender);
		empBean.setPhoneNumber(phoneNumber);
		empBean.setBirthdate(birthdate);
		empBean.setAddress(address);
		empBean.setNote(note);
		
		empService.updateOne(id,empBean);
		return "pass";
	}
	
	//員工帳號建立時判斷是否已被使用
	@RequestMapping(path = "/SelectPhone.Controller")
	@ResponseBody
	public String processSelectPhone(@RequestParam("phoneNumber") String phone) {
		System.out.println(phone);
		if(phone=="") {
			return "";
		}
		EmployeeBean empBean = new EmployeeBean();
		empBean.setPhoneNumber(phone);
		String selectPhone = empService.selectPhone(empBean);
		System.out.println(selectPhone);
		
		return selectPhone;
	}
	
	
	
	
}
