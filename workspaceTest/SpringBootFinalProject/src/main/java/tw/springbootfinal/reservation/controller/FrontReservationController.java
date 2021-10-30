package tw.springbootfinal.reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.fastjson.JSONObject;

import tw.springbootfinal.reservation.model.AdoptReservation;
import tw.springbootfinal.reservation.model.ReservationService;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
@RequestMapping("/users/petreserve")
public class FrontReservationController {

	@Autowired
	private ReservationService rsService;
	@Autowired
	private CustomerService cService;
	
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
	
	
	//檢查是否重複
	@PostMapping("/checkthenupdate") @ResponseBody
	public Integer checkIfRepeat(AdoptReservation temp, Model m) throws UnsupportedEncodingException {
		AdoptReservation check = rsService.selectOne(temp);
		if(check==null) {
			rsService.insertOrUpdate(temp);
			return 1;
		}else {
			return 2;
		}
	}
	
}
