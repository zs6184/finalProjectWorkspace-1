package tw.finalspring.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tw.finalspring.model.PetBean;
import tw.finalspring.service.PetService;
	
	
@Controller
public class PetInfoController {
	
	int ID;//選中資料的ID值
	List<PetBean> arrPet = new ArrayList<PetBean>();//查詢後用於顯示的資料存放處
	Set<String> sexSet = new HashSet<String>(); //查詢欄位的性別欄選項
	Set<String> cateSet = new HashSet<String>();//查詢欄位的類別欄選項
	
//--------------------------------------------------------------------
	
	//載入所有未領養寵物資訊
	@RequestMapping(path = "/petinfo.controller",method = RequestMethod.GET)
	public String processLoadingPage(Model m) throws UnsupportedEncodingException {
		arrPet = loadPet();
		sexSet.clear();		//進行內容刷新避免舊資料殘留
		cateSet.clear();	//進行內容刷新避免舊資料殘留
		Map<Integer,String> baseStr = new HashMap<>();
		
		for(PetBean aPet:arrPet) {
			sexSet.add(aPet.getSex());		//用Set將重複值篩選掉
			cateSet.add(aPet.getCategory());//用Set將重複值篩選掉
			
			byte[] base64 = Base64.encodeBase64(aPet.getPic()); //轉成base64 byte陣列
			String base64Str = new String (base64,"UTF-8"); //轉成UTF-8編碼字串
			baseStr.put(aPet.getPetId(), base64Str);//把字串放到MAP中使用PrtId作為KEY值
		}
		
		m.addAttribute("arrPet",arrPet);
		m.addAttribute("cateSet",cateSet);
		m.addAttribute("sexSet",sexSet);
		m.addAttribute("baseStr",baseStr);	//把base64字串MAP用另外的變數送出
		
		System.out.println(cateSet);
		System.out.println(sexSet);
		System.out.println(arrPet);
		
		return "PetInfo";
	}
	
	//使用條件進行查詢
	@RequestMapping(path = "/searchdatafront.controller",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String processSearchData(@RequestParam("category") String category,@RequestParam("sex") String sex,Model m) {
		
		arrPet = searchPet(category, sex);
		
		m.addAttribute("arrPet",arrPet);
		m.addAttribute("cateSet",cateSet);
		m.addAttribute("sexSet",sexSet);
		
		if(arrPet==null||arrPet.isEmpty()) {
			return "redirect:/petinfo.controller";
		}
		return "PetInfo";
	}

	
	
	//--------------------------------------------------------------------

	@Autowired
	private PetService pService;	
	
	// 載入全部未領養寵物資訊
	private List<PetBean> loadPet() {
		List<PetBean>tempArr = new ArrayList<>();
		try {
			tempArr = pService.selectNoAdopt();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tempArr;
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
