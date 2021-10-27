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

@Controller
@RequestMapping("/backstage/reservation")
public class BackReservationController {
	
	@Autowired
	private ReservationService rsService;
	
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
		
		return "BackReservation";
	}
}
