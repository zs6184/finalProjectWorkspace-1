package tw.springbootfinal.users.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;
import tw.springbootfinal.users.model.EmployeeBean;
import tw.springbootfinal.users.model.EmployeeService;

@Controller
@RequestMapping(path = "/Backstage")
public class EmployeesController {

	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private CustomerService cusService;

	@RequestMapping("/Employees.Controller")
	public String processEmployees() {
		return "employees";
	}

	// 抓取所有資料更新頁面
	@RequestMapping("/EmployeesAll.Controller")
	public String processSelectEmployeesAll(@SessionAttribute("username") String username,Model m, HttpServletRequest request) {
		List<EmployeeBean> selectAll = empService.findAll();

		// 此處無法使用@RequestParam("id")來抓取id，網頁會陷入過多重新導向問題，因此改採session方式抓會員資料
		CustomerBean cusBean = cusService.getByCusUsername(username);
		// 取得資料庫資料
		int cusId = cusBean.getCusId();
		String imageName = cusBean.getImageName();
		byte[] image = cusBean.getImage();
		System.out.println("imageName: " + imageName);
		// 如果會員沒有上傳過圖片就使用預設圖片
		if (image == null) {
			System.out.println("照片名null");
			m.addAttribute("imageName", "husky.jpg");
		} else {
			// 抓到專案路徑加上暫存資料夾名稱
			String path = request.getSession().getServletContext().getRealPath("/") + "downloadTempDir\\";
			System.out.println(path);
			cusService.imageDownload(image, cusId, imageName, path);

			m.addAttribute("imageName", imageName);
		}

		m.addAttribute("emp", selectAll);
		return "employees";
	}

	// 搜尋帳號最大值
	@RequestMapping("/CreateEmpUsername.Controller")
	@ResponseBody
	public String processCreateEmpUsername() {
		System.out.println("Controller");
		String empUsername = empService.findMaxEmpUsername();// 搜尋帳號最大值(最新)
		System.out.println(empUsername);

		return empUsername;
	}

	// 新建員工帳號
	@RequestMapping(path = "/CreateEmpAccount.Controller", method = RequestMethod.POST)
	@ResponseBody
	public String processCreateEmpAccount(EmployeeBean eBean) {

		String empPassword = eBean.getEmpPassword();
		// 密碼加密
		String encodePwd = new BCryptPasswordEncoder().encode(empPassword);
		eBean.setEmpPassword(encodePwd);
		String title = eBean.getTitle();

		// 如果職稱是店長就給予管理權限
		if ("store manager".equals(title)) {
			eBean.setRole("ADMIN");
		} else {// 否則給予員工權限
			eBean.setRole("EMPLOYEE");
		}

		EmployeeBean empBean = empService.save(eBean);
		System.out.println(empBean);
		return "pass";
	}

	// 搜尋指定員工資料
	@RequestMapping(path = "/SelectEmployeeById.Controller")
	@ResponseBody
	public EmployeeBean processSelectEmployeeById(@RequestParam("id") int id) {
		EmployeeBean selectOne = empService.findById(id);
		return selectOne;
	}

	// 刪除指定員工資料
	@RequestMapping(path = "/DeleteEmployeeById.Controller")
	@ResponseBody
	public boolean processDeleteEmployeeById(@RequestParam("id") int id) {
		boolean deleteOne = empService.deleteById(id);
		return deleteOne;

	}

	// 更新員工資料
	@RequestMapping(path = "/UpdateEmployeeById.Controller", method = RequestMethod.POST)
	@ResponseBody
	public String processUpdateEmployeeById(@RequestParam("empUpdateId") int id,
			@RequestParam("empNameUpdate") String empName, @RequestParam("hiredateUpdate") String hiredate,
			@RequestParam("titleUpdate") String title, @RequestParam("genderUpdate") String gender,
			@RequestParam("phoneNumberUpdate") String phoneNumber, @RequestParam("birthdateUpdate") String birthdate,
			@RequestParam("addressUpdate") String address, @RequestParam("notesUpdate") String note) {
		System.out.println("----------------");
		System.out.println(title);

		// 取得指定員工原始資料
		EmployeeBean empBean = empService.findById(id);
		// 將要更新的資料放到bean覆蓋原始資料
		empBean.setEmpRealname(empName);
		empBean.setHiredate(hiredate);
		empBean.setTitle(title);
		empBean.setGender(gender);
		empBean.setPhoneNumber(phoneNumber);
		empBean.setBirthdate(birthdate);
		empBean.setAddress(address);
		empBean.setNote(note);

		// 如果職稱是店長就給予管理權限
		if ("store manager".equals(title)) {
			empBean.setRole("ADMIN");
		} else {// 否則給予員工權限
			empBean.setRole("EMPLOYEE");
		}

		empService.save(empBean);
		return "pass";
	}

	// 員工帳號建立時判斷是否已被使用
	@RequestMapping(path = "/SelectPhone.Controller")
	@ResponseBody
	public String processSelectPhone(@RequestParam("phoneNumber") String phone) {
		System.out.println(phone);
		if (phone == "") {
			return "";
		}
		EmployeeBean empBean = new EmployeeBean();
		empBean.setPhoneNumber(phone);
		String selectPhone = empService.findByPhoneNumber(empBean);
		System.out.println(selectPhone);

		return selectPhone;
	}

}
