package tw.springbootfinal.reservation.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.springbootfinal.reservation.model.AdoptReservation;
import tw.springbootfinal.reservation.model.ReservationService;

@Controller
@RequestMapping("/users/petreserve")
public class FrontReservationController {

	@Autowired
	private ReservationService rsService;
	
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
