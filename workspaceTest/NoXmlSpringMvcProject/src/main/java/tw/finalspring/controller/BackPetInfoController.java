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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;

import tw.finalspring.model.CustomerBean;
import tw.finalspring.model.PetBean;
import tw.finalspring.service.CustomerService;
import tw.finalspring.service.PetService;

@Controller
public class BackPetInfoController {

	int ID;//選中資料的ID值
	List<PetBean> arrPet = new ArrayList<PetBean>();//查詢後用於顯示的寵物資料存放處
	List<CustomerBean> arrCus = new ArrayList<CustomerBean>();//查詢後用於顯示的客戶資料存放處
	Set<String> sexSet = new HashSet<String>(); //查詢欄位的性別欄選項
	Set<String> cateSet = new HashSet<String>();//查詢欄位的類別欄選項
	
//----------------------------------------------------------------	
	
	//取得全部會員資料放到變數中後續檢測+填值用
	@RequestMapping(path = "/getAllCustomerData.controller",method = RequestMethod.GET)
	public void processGetAllCus(HttpServletResponse response)throws IOException {
		JSONObject cusData = new JSONObject();
		JSONArray jsonCusArr = new JSONArray();// 存放多筆cus資料的json陣列
		int index = 0;
		
		arrCus = selectAllCus();
		
		//將客戶資料轉為JSON後回傳給AJAX請求
		for(CustomerBean aCus:arrCus) {
			String cusStr = (JSON.toJSONString(aCus,SerializerFeature.WriteMapNullValue));//Bean轉為JSON字串
			JSONObject cusObj = JSONObject.parseObject(cusStr); //字串轉為JSON物件
			jsonCusArr.add(index,cusObj);//JSON物件逐個放入JSON陣列
			index++;
		}
		System.out.println(jsonCusArr);
		cusData.put("cusData", jsonCusArr);
		PrintWriter out = response.getWriter();
		out.println(cusData);
		out.close();
		System.out.println("所有客戶輸出完成");
	}
	
	
	//載入全部寵物資料，寵物後台首頁
	@RequestMapping(path = "/backpetinfo.controller", method = RequestMethod.GET)
	public String processLoadingPage(Model m) {
		
		arrPet = loadPet();
		//處理類別與性別篩選
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
	public String processAddPetInfo(PetBean temp,@RequestParam("mypic") MultipartFile pic) throws JsonProcessingException {
		System.out.println("receive POST request");
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 寫出所有null後將所有空白轉null
		PetBean transfer = JSON.parseObject(jsonStr, PetBean.class);
		
		savePet(transfer,pic);

		return "redirect:/backpetinfo.controller";
	}
	
	//使用更新按鈕選擇寵物個別資料(含領養)
	@RequestMapping(path = "/selectbyid.controller",method = RequestMethod.GET)
	public void processSelectById(@RequestParam int id, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");

		//按下更新後取得的petId放到全域變數中保存
		ID=id; 
		PetBean temp = selectPet(id);

		//資料轉JSON字串
		String petStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue));
		JSONObject petData = JSONObject.parseObject(petStr);
		System.out.println(petStr);
		

		PrintWriter out = response.getWriter();
		out.print(petData);
		out.close();
		System.out.println("輸出完成");
	}
	
	//刪除單一資料
	@RequestMapping(path="/deletebyid.controller",method = RequestMethod.GET)
	public String processDeleteById(@RequestParam int id) {
		
		deletePet(id);
		System.out.println("Delete Success");
		
		return "BackPetInfo";
	}
	
	//修改單筆資料
	@RequestMapping(path = "/updateone.controller",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String processUpdateOne(PetBean temp,@RequestParam("mypic") MultipartFile pic) {
		
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 將所有空白轉為null
		PetBean transfer = JSON.parseObject(jsonStr, PetBean.class);
		
		updateOne(ID,transfer,pic);
		
		return "redirect:/backpetinfo.controller";
	}
	
	
	
//------------------------------------------------------
	@Autowired
	private PetService pService;
	@Autowired
	private CustomerService cService;

	// 執行插入單筆function
	private void savePet(PetBean temp,MultipartFile pic) {
		try {
			pService.insertOne(temp,pic);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 載入全部寵物資料
	private List<PetBean> loadPet() {
		List<PetBean>tempArr = new ArrayList<>();
		try {
			tempArr = pService.selectAll();			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tempArr;
	}
	
	//載入全部會員資料
	private List<CustomerBean> selectAllCus() {
		List<CustomerBean> allCus = new ArrayList<>();
		try {
			allCus = cService.selectAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return allCus;
	}
	
	//使用ID執行單一查詢(按下寵物資料更新按鈕後)
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
	private void updateOne(int petId,PetBean temp,MultipartFile pic) {
		try {
			pService.updateOne(petId,temp,pic);
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
