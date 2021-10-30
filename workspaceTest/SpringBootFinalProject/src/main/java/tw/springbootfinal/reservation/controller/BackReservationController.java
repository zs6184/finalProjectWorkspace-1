package tw.springbootfinal.reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import tw.springbootfinal.reservation.model.AdoptReservation;
import tw.springbootfinal.reservation.model.ReservationService;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
@RequestMapping("/backstage/reservation")
public class BackReservationController {
	
	@Autowired
	private ReservationService rsService;
	@Autowired
	private CustomerService cService;
	
	//取得所有資料 
	@GetMapping("/getAll")
	public String findAll(Model m){
		List<AdoptReservation> arrRes = rsService.findAll();
		
		m.addAttribute("arrRes",arrRes);
		
		return "BackReservation";
	}
	
	//根據複合主鍵取得單筆
	@GetMapping("/selectone")
	public void selectOne(AdoptReservation temp,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		AdoptReservation getData = rsService.selectOne(temp);
		String dataStr = (JSON.toJSONString(getData));
		JSONObject theData = JSONObject.parseObject(dataStr);
		
		PrintWriter out = response.getWriter();
		out.print(theData);
		out.close();
	}
	
	//新增或修改一筆
	@PostMapping("/addorupdate")
	public String insertOrUpdate(AdoptReservation temp) {
		rsService.insertOrUpdate(temp);
		return "redirect:/backstage/reservation/getAll";
	}
	
	//刪除一筆
	@GetMapping("/deleteOne")
	public String deleteOne(AdoptReservation temp) {
		rsService.deleteOne(temp);
		
		return "redirect:/backstage/reservation/getAll";
	}
	
	//處理失約的情形並做紀錄
	@GetMapping("/dealmissing")
	public void dealMissing(AdoptReservation temp) {
		//客戶失約+1
		CustomerBean theCus = cService.findById(temp.getCusId());
		int misscount =theCus.getNoShow();
		misscount++;
		theCus.setNoShow(misscount);
		System.out.println("客戶ID:"+theCus.getCusId()+"失約紀錄+1完成");
		cService.save(theCus);
		//處理預約紀錄狀態
		AdoptReservation theRecord = rsService.selectOne(temp);
		String status =theRecord.getKeepStatus();
		status="失約";
		theRecord.setKeepStatus(status);
		rsService.insertOrUpdate(theRecord);
	}
}
