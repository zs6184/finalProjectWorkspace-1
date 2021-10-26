package tw.springbootfinal.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	//新增一筆
	@PostMapping("/add") @ResponseBody
	public AdoptReservation insertOne(@RequestBody AdoptReservation ar) {
		return rsService.insertOne(ar);
	}
}
