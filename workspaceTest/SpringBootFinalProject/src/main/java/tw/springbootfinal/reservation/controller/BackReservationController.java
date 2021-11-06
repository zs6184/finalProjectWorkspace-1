package tw.springbootfinal.reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import tw.springbootfinal.reservation.model.AdoptReservation;
import tw.springbootfinal.reservation.model.ReservationService;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
@RequestMapping("/Backstage/reservation")
public class BackReservationController {
	
	@Autowired
	private ReservationService rsService;
	@Autowired
	private CustomerService cService;
	
	Integer preID;
	String preTime;
	int status;
//------------------------------------------------------------------	
	
	//取得所有資料 
	@GetMapping("/getAll")
	public String findAll(@SessionAttribute("username")String username,HttpServletRequest request,Model m){
		List<AdoptReservation> arrRes = rsService.findAll();
		
		m.addAttribute("arrRes",arrRes);
		if(status==1) {
			m.addAttribute("status","已預約");
			status=0;
		}
		//會員圖片
		String imageName = cService.selectImage(username, request);
		m.addAttribute("imageName",imageName);
		
		return "BackReservation";
	}
	
	//根據複合主鍵取得單筆
	@GetMapping("/selectone")
	public void selectOne(AdoptReservation temp,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		AdoptReservation getData = rsService.selectOne(temp);
		preID=getData.getCusId();
		preTime=getData.getReserveTime();
		System.out.println("preID="+preID+"preTime"+preTime);
		String dataStr = (JSON.toJSONString(getData));
		JSONObject theData = JSONObject.parseObject(dataStr);
		
		PrintWriter out = response.getWriter();
		out.print(theData);
		out.close();
	}
	
	//新增一筆
	@PostMapping("/addone")
	public String insertOne(AdoptReservation temp) {
		AdoptReservation check= rsService.selectOne(temp);
		if(check==null) {
			rsService.insertOne(temp);
		}else {
			status=1;
		}
		
		return "redirect:/Backstage/reservation/getAll";
	}
	
	//修改一筆
	@PostMapping("/updateone")
	public String updateOne(AdoptReservation temp,Model m) {
		AdoptReservation preRec=rsService.selectByMultiKey(preID, preTime);
		AdoptReservation check = rsService.selectOne(temp);
		//如果此主鍵尚未被使用：插入新值、刪去舊值
		if(check==null) {
			rsService.insertOne(temp);
			rsService.deleteOne(preRec);
		}
		else {//若此主鍵已經有人使用(相同會員同一日已有預約)
			if(temp.getCusId()==preID && temp.getReserveTime().equals(preTime)) {//確認主鍵相同時只更新赴約狀態
				check.setKeepStatus(temp.getKeepStatus());
				rsService.updateOne(check);
			}else {//主鍵有重複且並非同一筆資料
				status=1;
			}
		}
		return "redirect:/Backstage/reservation/getAll";
	}
	
	
	//刪除一筆
	@GetMapping("/deleteOne")
	public String deleteOne(AdoptReservation temp) {
		rsService.deleteOne(temp);
		
		return "redirect:/Backstage/reservation/getAll";
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
		rsService.insertOne(theRecord);
	}
}
