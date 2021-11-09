package tw.springbootfinal.booking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.fastjson.JSONObject;

import tw.springbootfinal.booking.model.BookingService;
import tw.springbootfinal.booking.model.BookingsBean;
import tw.springbootfinal.booking.model.BookingsDTO;
import tw.springbootfinal.booking.model.Constant;
import tw.springbootfinal.reservation.model.AdoptReservation;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
@RequestMapping("/Users/bookingsRecord")
public class FrontBookingController {
	
	@Autowired
	private BookingService bService;
	@Autowired
	private CustomerService cService;
	
	@Autowired
	private BookingService bookingService;
	
	int status;
	
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
		
		
	
		@GetMapping("/getthecusresult")
		public String slelectTheCusResult(@SessionAttribute("username")String username,Model m,HttpServletRequest request) {
			CustomerBean temp = cService.getByCusUsername(username);
			Integer theId = temp.getCusId();
			int arrive=0;
			int notyet=0;
			int miss=0;
			List<BookingsBean> arrBoo = bService.selectTheCusRec(theId);
			for(BookingsBean aBoo:arrBoo) {
				switch(aBoo.getKeepStatus()) {
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
			m.addAttribute("arrBoo",arrBoo);
			if(status==1) {
				m.addAttribute("status","已預約");
				status=0;
			}
			//會員圖片
			String imageName = cService.selectImage(username, request);
			m.addAttribute("imageName",imageName);

			return "CusBookingCheck";
		}
		
		
		
		/**
		 * 訂位
		 * 
		 * @param bookingsDTO
		 * @return
		 */
		@ResponseBody()
		@PostMapping(produces = "application/json; charset=utf-8")
		public String create(@RequestBody BookingsDTO bookingsDTO) {
			bookingService.save(bookingsDTO);
			return "　　訂位成功";
		}
		
		
		//刪除一筆
		/**
		 * 刪除單一資料 (Ajax)
		 */
		@DeleteMapping("{id}")
		@ResponseBody()
		public String processDeleteById(@PathVariable String id, HttpServletResponse response) {
//			throw new AjaxException("刪除失敗");
			System.out.println("Start ---- Delete");
			System.out.println(id);

			bookingService.delete(Integer.parseInt(id));
			System.out.println("Delete Success");
			return Constant.DELETE_SUCCESS;
		}
		
}
