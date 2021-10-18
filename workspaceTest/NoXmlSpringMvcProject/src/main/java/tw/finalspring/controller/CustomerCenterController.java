package tw.finalspring.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import tw.finalspring.model.CustomerBean;
import tw.finalspring.service.CustomerService;

@Controller
@SessionAttributes(value = { "cusId" })
public class CustomerCenterController {

	@Autowired
	private CustomerService cusService;

	@RequestMapping(path = "/SelectCustomer.controller")
//	@ResponseBody
	public String processSelectCustomer(HttpSession session, Model m, HttpServletRequest request) {
		// 取得登入時session層級的username
		Object attr = session.getAttribute("username");
		String str = attr.toString();

		CustomerBean cusBean = new CustomerBean();
		cusBean.setCusUsername(str);//設定username到Bean
		// 利用username取得list結果集
		List<CustomerBean> cus = cusService.selectCustomerCenterUsername(cusBean);
		m.addAttribute("cus", cus);// 將結果設成屬性給jsp使用

		int cusId = 0;
		String imageName = null;
		byte[] image = {};
		//取得資料庫資料
		for (CustomerBean customerBean : cus) {
			cusId = customerBean.getCusId();
			imageName = customerBean.getImageName();
			image = customerBean.getImage();
			System.out.println(imageName);
		}
		// 如果會員沒有上傳過圖片就使用預設圖片
		if (image == null) {
			m.addAttribute("imageName", "husky.jpg");
		} else {
			// 抓到專案路徑加上暫存資料夾名稱
			String path = request.getSession().getServletContext().getRealPath("/") + "downloadTempDir\\";

			cusService.imageDownload(image, cusId, imageName, path);

			m.addAttribute("imageName", imageName);
		}

		return "customerCenter";
	}

	// 會員中心資料更新
	@RequestMapping(path = "/UpdateCustomer.controller", method = RequestMethod.POST)
	@ResponseBody
	public String processUpdateCustomer(@RequestParam("image") MultipartFile mFile, @RequestParam("cusId") int cusId,
			@RequestParam("cusRealname") String realName, @RequestParam("aka") String aka,
			@RequestParam("gender") String gender, @RequestParam("birthdate") String birthdate,
			@RequestParam("phoneNumber") String phone, @RequestParam("email") String email,
			@RequestParam("address") String address, Model m, HttpServletRequest request)
			throws IllegalStateException, IOException {

		CustomerBean cusBean = new CustomerBean();
		cusBean.setCusId(cusId);
		cusBean.setCusRealname(realName);
		cusBean.setAka(aka);
		cusBean.setGender(gender);
		cusBean.setBirthdate(birthdate);
		cusBean.setPhoneNumber(phone);
		cusBean.setEmail(email);
		cusBean.setAddress(address);

		// 將基本資料寫進資料庫
		cusService.updateCustomerCenter(cusId, cusBean);

		String filename = mFile.getOriginalFilename();// 取得圖檔原始名稱
		String cusFilename = cusId + "_" + filename;

		// 取得專案路徑並加上要放圖片的資料夾名稱
		String saveFileDir = request.getSession().getServletContext().getRealPath("/") + "uploadTempDir\\";

		File file = new File(saveFileDir);// 放入路徑
		file.mkdirs();// 建立資料夾

		String saveFilePath = saveFileDir + cusFilename;// 路徑+檔名

		File saveFile = new File(saveFilePath);
		mFile.transferTo(saveFile);// 存到硬碟

		System.out.println(cusFilename);
		// 如果檔案存在，就將資料夾裡的圖片存到資料庫
		if (filename != null && filename.length() != 0) {
			cusService.saveFile(cusId, cusFilename, saveFilePath);
		}

		saveFile.delete();// 刪除存在硬碟的檔案

		return "pass";
	}

}
