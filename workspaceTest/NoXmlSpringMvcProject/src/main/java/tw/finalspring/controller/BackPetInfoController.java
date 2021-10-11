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

import tw.finalspring.model.PetBean;
import tw.finalspring.service.PetService;

@Controller
public class BackPetInfoController {

	int ID;//選中資料的ID值
	List<PetBean> arrPet = new ArrayList<PetBean>();//查詢後用於顯示的資料存放處
	Set<String> sexSet = new HashSet<String>(); //查詢欄位的性別欄選項
	Set<String> cateSet = new HashSet<String>();//查詢欄位的類別欄選項
	
//----------------------------------------------------------------	
	
	//載入全部資料
	@RequestMapping(path = "/backpetinfo.controller", method = RequestMethod.GET)
	public String processLoadingPage(Model m) {
		arrPet = loadPet();
		sexSet.clear();		//進行內容刷新避免舊資料殘留
		cateSet.clear();	//進行內容刷新避免舊資料殘留
		
		for(PetBean aPet:arrPet) {
			sexSet.add(aPet.getSex());		//用Set將重複值篩選掉
			cateSet.add(aPet.getCategory());//用Set將重複值篩選掉
		}
	
		m.addAttribute("arrPet",arrPet);
		m.addAttribute("cateSet",cateSet);
		m.addAttribute("sexSet",sexSet);
		
		
		return "BackPetInfo";
	}
	
	//使用條件進行查詢
	@RequestMapping(path = "/searchdata.controller",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String processSearchData(@RequestParam("category") String category,@RequestParam("sex") String sex,Model m) {
		
		arrPet = searchPet(category, sex);
		
		m.addAttribute("arrPet",arrPet);
		m.addAttribute("cateSet",cateSet);
		m.addAttribute("sexSet",sexSet);
		
		if(arrPet==null||arrPet.isEmpty()) {
			return "redirect:/backpetinfo.controller";
		}
		
		return "BackPetInfo";
	}
	
	
	//新增單一資料
	@RequestMapping(path = "/insertPetInfo.controller", method = RequestMethod.POST, produces = "application/json; charset=utf-8") // 設定字串type與編碼
	public String processAddPetInfo(PetBean temp) throws JsonProcessingException {
		System.out.println("receive POST request");
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 寫出所有null後將所有空白轉null
		PetBean transfer = JSON.parseObject(jsonStr, PetBean.class);
			
		savePet(transfer);

		return "redirect:/backpetinfo.controller";
	}
	
	//選取單一資料
	@RequestMapping(path = "/selectbyid.controller",method = RequestMethod.GET)
	public void processSelectById(@RequestParam String id, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		System.out.println("Start To Select");
		System.out.println(id);
		int petId = Integer.parseInt(id);
		PetBean temp = selectPet(petId);
		ID=petId; //按下更新後取得的petId放到全域變數中保存
		
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue));
		JSONObject jsonObj = JSONObject.parseObject(jsonStr);
		System.out.println(jsonStr);
		
		PrintWriter out = response.getWriter();
		out.print(jsonObj);
		out.close();
		System.out.println("輸出完成");
	}
	
	//刪除單一資料
	@RequestMapping(path="/deletebyid.controller",method = RequestMethod.GET)
	public String processDeleteById(@RequestParam int id) {
		
		deletePet(id);
		System.out.println("Delete Success");
		
		return "redirect:/backpetinfo.controller";
	}
	
	//修改單筆資料
	@RequestMapping(path = "/updateone.controller",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String processUpdateOne(PetBean temp) {
		
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 將所有空白轉為null
		PetBean transfer = JSON.parseObject(jsonStr, PetBean.class);
		
		updateOne(ID,transfer);
		
		return "redirect:/backpetinfo.controller";
	}
	
	
	
//------------------------------------------------------
	@Autowired
	private PetService pService;

	// 執行插入單筆function
	private void savePet(PetBean temp) {
		try {
			pService.insertOne(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 執行載入全部function
	private List<PetBean> loadPet() {
		List<PetBean>tempArr = new ArrayList<>();
		try {
			tempArr = pService.selectAll();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tempArr;
	}
	
	//使用ID執行單一查詢
	private PetBean selectPet(int petId) {
		PetBean temp = new PetBean();
		try {
			temp = pService.selectById(petId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	//使用ID進行刪除
	private void deletePet(int petId) {
		try {
			pService.deleteById(petId);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//更新單筆資料
	private void updateOne(int petId,PetBean temp) {
		try {
			pService.updateOne(petId,temp);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//使用類別與性別查詢
	private List<PetBean> searchPet(String category,String sex){
		List<PetBean>tempArr = new ArrayList<>();
		try {
			tempArr = pService.searchData(category, sex);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tempArr;
	}
	
	

}
