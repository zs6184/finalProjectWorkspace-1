package tw.springbootfinal.users.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
@RequestMapping(path = "/Backstage")
public class CustomersController {

	@Autowired
	private CustomerService cusService;

	@GetMapping("/Customer.Controller")
	public String processCustomer() {
		return "Customer";
	}

	// 查詢全部會員
	@GetMapping("/SelectCustomerAll.Controller")
	public String processSelectCustomerAll(@SessionAttribute("username") String username,Model m, HttpServletRequest request) {
		List<CustomerBean> selectAll = cusService.findAll();// 搜尋所有會員資料
		//此處無法使用@RequestParam("id")來抓取id，網頁會陷入過多重新導向問題，因此改採session方式抓會員資料
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
		
		// 將整個陣列傳到jsp的forEach
		m.addAttribute("cus", selectAll);
		return "Customer";
	}

	// 用Id查詢指定會員
	@GetMapping("/SelectCustomerImageById.Controller")
	@ResponseBody
	public CustomerBean processSelectCustomerById(@RequestParam("id") int id, Model m, HttpServletRequest request) {
		CustomerBean cusBean = cusService.findById(id);
		System.out.println("id: " + id);
		// 取得資料庫資料
		int cusId = cusBean.getCusId();
		String imageName = cusBean.getImageName();
		byte[] image = cusBean.getImage();
		System.out.println("imageName: " + imageName);
		// 如果會員沒有上傳過圖片就使用預設圖片
		if (image == null) {
			System.out.println("照片名null");
			// m.addAttribute("imageName", "husky.jpg");
			cusBean.setImageName("husky.jpg");
		} else {
			// 抓到專案路徑加上暫存資料夾名稱
			String path = request.getSession().getServletContext().getRealPath("/") + "downloadTempDir\\";
			System.out.println(path);
			cusService.imageDownload(image, cusId, imageName, path);

			m.addAttribute("imageName", imageName);
		}

		System.out.println(cusBean);// 查看會員資料
		return cusBean;
	}

	// 刪除指定會員
	@RequestMapping(path = "/DeleteCustomerById.Controller")
	@ResponseBody
	public String processDeleteCustomerById(@RequestParam("id") int id) {
		cusService.deleteById(id);
		return "true";
	}

	// 更新指定會員
	@PostMapping("/UpdateCustomerById.Controller")
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
