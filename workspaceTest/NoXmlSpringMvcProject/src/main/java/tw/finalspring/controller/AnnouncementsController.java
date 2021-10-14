package tw.finalspring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

import tw.finalspring.model.AnnouncementsBean;
import tw.finalspring.service.AnnouncementsService;

@Controller
public class AnnouncementsController {

	int ID;
	
//	載入全部資料
	@RequestMapping(path = "/announcements.controller", method = RequestMethod.GET)
	public String processLoadingPage(Model m) {
		List<AnnouncementsBean> arrAnnounce = loadAnnounce();
		m.addAttribute("arrAnnounce",arrAnnounce);
		
		return "Announcements";

	
	}
	
	//新增單一資料
	@RequestMapping(path = "/insertAnnouncements.controller", method = RequestMethod.POST, produces = "application/json; charset=utf-8") // 設定字串type與編碼
	public String processAddAnnouncements(AnnouncementsBean temp) throws JsonProcessingException {
		System.out.println("receive POST request");
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 寫出所有null後將所有空白轉null
		AnnouncementsBean transfer = JSON.parseObject(jsonStr, AnnouncementsBean.class);
			
		saveAnnounce(transfer);

		return "redirect:/announcements.controller";
	}
	
	//選取單一資料
	@RequestMapping(path = "/announcementsSelectbyid.controller",method = RequestMethod.GET)
	public void processSelectById(@RequestParam String id, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		System.out.println("Start ------ Select");
		System.out.println(id);
		int announceID = Integer.parseInt(id);
		AnnouncementsBean temp = selectAnnounce(announceID);
		ID=announceID; //按下更新後取得的Announcements放到全域變數中保存
		
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue));
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		System.out.println(jsonStr);
		
		PrintWriter out = response.getWriter();
		out.print(jsonObj);
		out.close();
		System.out.println("輸出完成OK");
	}
	
	//刪除單一資料
	@RequestMapping(path="/announcementsDeletebyid.controller",method = RequestMethod.GET)
	public String processDeleteById(@RequestParam String id) {
		System.out.println("Start ---- Delete");
		System.out.println(id);
		
		int announceID = Integer.parseInt(id);
		deleteAnnounce(announceID);
		System.out.println("Delete Success");
		
		return "redirect:/announcements.controller";
	}
	
	//修改單筆資料
	@RequestMapping(path = "/announcementsUpdateone.controller",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String processUpdateOne(AnnouncementsBean temp) {
		
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 將所有空白轉為null
		AnnouncementsBean transfer = JSON.parseObject(jsonStr, AnnouncementsBean.class);
		
		updateOne(ID,transfer);
		
		return "redirect:/announcements.controller";
	}
	
	
	
//------------------------------------------------------
	@Autowired
	private AnnouncementsService aService;

	// 執行插入單筆function
	private void saveAnnounce(AnnouncementsBean temp) {
		try {
			aService.insertOne(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 執行載入全部function
	private List<AnnouncementsBean> loadAnnounce() {
		List<AnnouncementsBean> tempArr = new ArrayList<>();
		try {
			tempArr = aService.selectAll();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tempArr;
	}
	
	//使用ID執行單一查詢
	private AnnouncementsBean selectAnnounce(int announceID) {
		AnnouncementsBean temp = new AnnouncementsBean();
		try {
			temp = aService.selectById(announceID);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return temp;
	}
	
	//使用ID進行刪除
	private void deleteAnnounce(int announceID) {
		try {
			aService.deleteById(announceID);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//更新單筆資料
	private void updateOne(int announceID,AnnouncementsBean temp) {
		try {
			aService.updateOne(announceID,temp);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
