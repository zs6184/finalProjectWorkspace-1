package tw.springbootfinal.reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import tw.springbootfinal.reservation.model.AdoptReservation;
import tw.springbootfinal.reservation.model.ReservationService;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;
import tw.springbootfinal.users.model.EmployeeBean;
import tw.springbootfinal.users.model.EmployeeService;

@Controller
@RequestMapping("/Users/petreserve")
public class FrontReservationController {

	@Autowired
	private ReservationService rsService;
	@Autowired
	private CustomerService cService;
	@Autowired
	private EmployeeService empService;
	
	int status;
	
//----------------------------------------------------------------	
	
	//確認已登入後回傳客戶資料
	@GetMapping("/checktheCus")
	public void returnTheCus(@SessionAttribute("username")String username,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		CustomerBean theCus = cService.getByCusUsername(username);
		JSONObject cusData = new JSONObject();
		cusData.put("theCus", theCus);
		PrintWriter out = response.getWriter();
		out.println(cusData);
		out.close();	
	}
	
	//確認已登入後回傳員工資料
	@GetMapping("/checktheEmp")
	public void returnTheEmp(@SessionAttribute("username")String username,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		EmployeeBean theEmp = empService.getByEmpUsername(username);
		JSONObject empData = new JSONObject();
		empData.put("theEmp", theEmp);
		PrintWriter out = response.getWriter();
		out.println(empData);
		out.close();	
	}	
	
	//檢查是否重複(SinglePetInfo進行預約時候判斷用)
	@PostMapping("/checkthenupdate") @ResponseBody
	public Integer checkIfRepeat(AdoptReservation temp, Model m) throws UnsupportedEncodingException {
		CustomerBean theCus = cService.findById(temp.getCusId());
		int count = theCus.getNoShow();
		if(count<5) {
			AdoptReservation check = rsService.selectOne(temp);
			if(check==null) {
				rsService.insertOne(temp);
				return 1;
			}else {
				return 2;
			}
		}else{
			return 3;
		}
	}
	
	//查詢單一客戶的領養預約紀錄給個人中心
	@GetMapping("/getthecusresult")
	public String slelectTheCusResult(@SessionAttribute("username")String username,@SessionAttribute("role")String role,Model m,HttpServletRequest request) {
		System.out.println("ROLE="+role);
		String imageName="";
		int notyet=0;
		int miss=0;
		int arrive=0;
		Integer theId=0;
		//個人中心圖片
		if("MEMBER".equals(role)) {
			imageName = cService.selectImage(username, request);
			CustomerBean temp = cService.getByCusUsername(username);
			theId = temp.getCusId();
		}else {
			imageName = empService.selectImage(username, request);
			EmployeeBean temp =empService.getByEmpUsername(username);
			theId = temp.getEmpId();
		}
		
		List<AdoptReservation> arrRes = rsService.selectTheCusRec(theId);
		for(AdoptReservation aRes:arrRes) {
			switch(aRes.getKeepStatus()) {
			case "已赴約":
				arrive++;
				break;
			case "未赴約":
				notyet++;
				break;
			case "失約":
				miss++;
				break;
			}
		}
		m.addAttribute("arrive",arrive);
		m.addAttribute("notyet",notyet);
		m.addAttribute("miss",miss);
		m.addAttribute("arrRes",arrRes);
		
		if(status==1) {
			m.addAttribute("status","已預約");
			status=0;
		}
		m.addAttribute("imageName",imageName);
		
		return "CusAdoptCheck";
	}
	
	//刪除一筆
	@GetMapping("/deleteOne")
	public String deleteOne(AdoptReservation temp) {
		rsService.deleteOne(temp);
		
		return "redirect:/Users/petreserve/getthecusresult";
	}

	//送出單筆修改
	@PostMapping("/updateone") @ResponseBody
	public int updateOne(@RequestParam("nowTime")String nowTime,@RequestParam("preTime")String preTime,@RequestParam("id")Integer id) {
		AdoptReservation preRec=rsService.selectByMultiKey(id, preTime);
		System.out.println("PreTime= "+preTime+" CUSID= "+id+"NowTime"+nowTime);
		AdoptReservation check = rsService.selectByMultiKey(id,nowTime);
		//如果此主鍵尚未被使用：插入新值、刪去舊值
		if(check==null) {
			rsService.deleteOne(preRec);
			preRec.setReserveTime(nowTime);
			rsService.updateOne(preRec);
			return 0;
		}
		else {//若此主鍵已經有人使用(相同會員同一日已有預約)
			return 1;	
		}
	}
	
	//根據複合主鍵取得單筆
	@GetMapping("/selectone")
	public void selectOne(AdoptReservation temp,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		AdoptReservation getData = rsService.selectOne(temp);

		System.out.println("preID="+getData.getCusId()+"preTime"+getData.getReserveTime());
		String dataStr = (JSON.toJSONString(getData));
		JSONObject theData = JSONObject.parseObject(dataStr);
		
		PrintWriter out = response.getWriter();
		out.print(theData);
		out.close();
	}

}
