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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import tw.springbootfinal.booking.model.BookingService;
import tw.springbootfinal.booking.model.BookingsBean;
import tw.springbootfinal.booking.model.BookingsDTO;
import tw.springbootfinal.booking.model.Constant;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
@RequestMapping("/backstage/bookings")
public class BookingsController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private CustomerService cService;
	int ID;

//	查詢全部資料
//	@GetMapping()
	@RequestMapping(method = { RequestMethod.GET })
	public String processLoadingPage(@SessionAttribute("username") String username, Model m, HttpServletRequest request) {
		List<BookingsBean> arrBook = bookingService.getAll();
		m.addAttribute("arrBook", arrBook);
		
		String imageName = cService.selectImage(username, request);
		m.addAttribute("imageName",imageName);
		
		return "Bookings";
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

	// 新增單一資料
//	@RequestMapping(value = "insert", method = RequestMethod.POST, produces = "application/json; charset=utf-8") // 設定字串type與編碼
	@PostMapping("insert")
	public String processAddBookings(BookingsBean temp) {
		bookingService.save(temp);
		return "redirect:/backstage/bookings";
	}

	// 選取單一資料
	@RequestMapping(path = "id/{id}", method = RequestMethod.GET)
	public void processSelectById(HttpServletResponse response, @PathVariable String id) throws IOException {
		response.setContentType("text/html;charset=utf-8");

		System.out.println("Start ------ Select");
		System.out.println(id);
		BookingsBean temp = bookingService.getById(Integer.parseInt(id));
//		ID=bookingsId; //按下更新後取得的booking放到全域變數中保存

		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue));
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		System.out.println(jsonStr);

		PrintWriter out = response.getWriter();
		out.print(jsonObj);
		out.close();
		System.out.println("輸出完成OK");
	}

	/**
	 * 刪除單一資料 (Ajax)
	 */
	@DeleteMapping("{id}")
	@ResponseBody()
	public String processDeleteById(@PathVariable String id, HttpServletResponse response) {
//		throw new AjaxException("刪除失敗");
		System.out.println("Start ---- Delete");
		System.out.println(id);

		bookingService.delete(Integer.parseInt(id));
		System.out.println("Delete Success");
		return Constant.DELETE_SUCCESS;
	}

	/**
	 * 修改單筆資料
	 * 
	 * @param temp
	 * @return
	 */
//	@RequestMapping(path = "/bookingsUpdateone.controller",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@PostMapping("update")
	public String processUpdateOne(BookingsBean temp) {

		bookingService.update(temp);
		return "redirect:/backstage/bookings";
	}

	
	
	
	
	//AJAX取得全部會員資料後續檢測+填值用
		@GetMapping("/getAllCustomerData.controller")
		public void processGetAllCus(HttpServletResponse response)throws IOException {
			response.setContentType("text/html;charset=utf-8");
			List<CustomerBean> arrCus = cService.findAll();
			JSONObject cusData = new JSONObject();
			cusData.put("cusData", arrCus);
			PrintWriter out = response.getWriter();
			out.println(cusData);
			out.close();
		}
		
}
