package tw.finalspring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;

import tw.finalspring.model.BookingsBean;
import tw.finalspring.model.PetBean;
import tw.finalspring.service.BookingsService;

@Controller
public class BookingsController {

	int ID;
	
//	載入全部資料
	@RequestMapping(path = "/bookings.controller", method = RequestMethod.GET)
	public String processLoadingPage(Model m) {
		List<BookingsBean> arrBook = loadBook();
		m.addAttribute("arrBook",arrBook);
		
		return "Bookings";

	
	}
	
	//新增單一資料
	@RequestMapping(path = "/insertBookings.controller", method = RequestMethod.POST, produces = "application/json; charset=utf-8") // 設定字串type與編碼
	public String processAddBookings(BookingsBean temp) throws JsonProcessingException {
		System.out.println("receive POST request");
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 寫出所有null後將所有空白轉null
		BookingsBean transfer = JSON.parseObject(jsonStr, BookingsBean.class);
			
		saveBook(transfer);

		return "redirect:/bookings.controller";
	}
	
	//選取單一資料
	@RequestMapping(path = "/bookingsSelectbyid.controller",method = RequestMethod.GET)
	public void processSelectById(@RequestParam String id, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		System.out.println("Start ------ Select");
		System.out.println(id);
		int bookingsId = Integer.parseInt(id);
		BookingsBean temp = selectBook(bookingsId);
		ID=bookingsId; //按下更新後取得的booking放到全域變數中保存
		
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue));
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		System.out.println(jsonStr);
		
		PrintWriter out = response.getWriter();
		out.print(jsonObj);
		out.close();
		System.out.println("輸出完成OK");
	}
	
	//刪除單一資料
	@RequestMapping(path="/bookingsDeletebyid.controller",method = RequestMethod.GET)
	public String processDeleteById(@RequestParam String id) {
		System.out.println("Start ---- Delete");
		System.out.println(id);
		
		int BookingsId = Integer.parseInt(id);
		deleteBook(BookingsId);
		System.out.println("Delete Success");
		
		return "redirect:/bookings.controller";
	}
	
	//修改單筆資料
	@RequestMapping(path = "/bookingsUpdateone.controller",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String processUpdateOne(BookingsBean temp) {
		
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 將所有空白轉為null
		BookingsBean transfer = JSON.parseObject(jsonStr, BookingsBean.class);
		
		updateOne(ID,transfer);
		
		return "redirect:/bookings.controller";
	}
	
	
	
//------------------------------------------------------
	@Autowired
	private BookingsService bService;

	// 執行插入單筆function
	private void saveBook(BookingsBean temp) {
		try {
			bService.insertOne(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 執行載入全部function
	private List<BookingsBean> loadBook() {
		List<BookingsBean>tempArr = new ArrayList<>();
		try {
			tempArr = bService.selectAll();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tempArr;
	}
	
	//使用ID執行單一查詢
	private BookingsBean selectBook(int bookingsId) {
		BookingsBean temp = new BookingsBean();
		try {
			temp = bService.selectById(bookingsId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	//使用ID進行刪除
	private void deleteBook(int bookingsId) {
		try {
			bService.deleteById(bookingsId);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//更新單筆資料
	private void updateOne(int bookingsId,BookingsBean temp) {
		try {
			bService.updateOne(bookingsId,temp);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
