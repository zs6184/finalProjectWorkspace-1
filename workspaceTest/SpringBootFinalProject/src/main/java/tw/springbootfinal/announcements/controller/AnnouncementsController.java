package tw.springbootfinal.announcements.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import tw.springbootfinal.announcements.model.Announcements;
import tw.springbootfinal.announcements.model.AnnouncementsService;

@Controller
@RequestMapping("/backstage/announcements")
public class AnnouncementsController {

	
	@Autowired
	private AnnouncementsService aService;
	
	/*
	 * 取得所有文章資料
	 */
	@GetMapping("/backannouncements.controller")
	public String processGetAll(Model m) {
		List<Announcements> arrAnnounce = aService.getAll();
		m.addAttribute("arrAnnounce",arrAnnounce);
		return "Announcements";
	}
	
	/*
	 * 新增文章資料
	 */
	@PostMapping("/insertAnnouncements.controller")
	public String process(Announcements temp,@RequestParam("mypic") MultipartFile pic) throws IOException {
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 寫出所有null後將所有空白轉null
		Announcements transfer = JSON.parseObject(jsonStr,Announcements.class);
		
		aService.insertOne(transfer, pic);
		
		return "redirect:/backstage/announcements/backannouncements.controller";
	}
	
	
	/*
	 * 選擇且查看文章資料
	 */
	@GetMapping("/selectbyid.controller")
	public void processSelectById(@RequestParam int id,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		Announcements temp = aService.selectById(id);
		//資料轉成json GOGO!!
		String annStr = (JSON.toJSONString(temp,SerializerFeature.WriteMapNullValue));
		JSONObject annData = JSONObject.parseObject(annStr);
		
		PrintWriter out = response.getWriter();
		out.print(annData);
		out.close();
		
		
	}
	
	/*
	 * 更新資料
	 */
	@PostMapping("/updateone.controller")
	public String processUpdateOne(Announcements temp,MultipartFile pic) throws IOException {
		String jsonStr = (JSON.toJSONString(temp,SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null");
		Announcements transfer = JSON.parseObject(jsonStr, Announcements.class);
		aService.updateOne(transfer, pic);
		System.out.println("修改完成");
		return "redirect:/backstage/announcements/backannouncements.controller";
		
		
	}
	
	
	/*
	 * 刪除資料
	 */
	@GetMapping("/deletebyid.controller")
	public String processDeleteById(@RequestParam int id) {
		aService.deleteById(id);
		
		return "Announcements";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
