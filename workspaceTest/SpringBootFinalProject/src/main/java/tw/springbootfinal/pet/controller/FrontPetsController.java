package tw.springbootfinal.pet.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.springbootfinal.pet.model.Pets;
import tw.springbootfinal.pet.model.PetsService;

@Controller
@RequestMapping("/pet")
@SessionAttributes(names = {"sexSet","cateSet"})
public class FrontPetsController {

	@Autowired
	private PetsService pService;
//--------------------------------------------------------------

	
	//取得所有未領養寵物資訊
	@GetMapping("/petinfo.controller")
	public String processLoadingPage(Model m) throws UnsupportedEncodingException {
		List<Pets>arrPet = pService.findNotAdopt();	//存放未領養寵物資料
		Set<String> sexSet = new HashSet<String>();	//篩選並回傳性別
		Set<String> cateSet = new HashSet<String>();//篩選並回傳種類
		Map<Integer,String> baseStr = new HashMap<Integer,String>();//以ID為KEY存放對應圖片
		
		for(Pets aPet:arrPet) {
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
		
		return "PetInfo";
	}
	
	
	
	//使用條件進行查詢(未領養者)
	@PostMapping("/searchdatafront.controller")
	public String processSearchData(Pets pet, Model m) throws UnsupportedEncodingException {
		String sex = pet.getSex();
		String category = pet.getCategory();
		Map<Integer,String> baseStr = new HashMap<>();
		List<Pets> tempAll = pService.findBySexAndCategory(sex, category);
		List<Pets> arrPet = new ArrayList<Pets>();
		
		if(tempAll==null) return "redirect:/pet/petinfo.controller";
		
		for(Pets aPet:tempAll) {
			if(aPet.getAdoptStatus().equals("未領養")) {
				arrPet.add(aPet);
				byte[] base64 = Base64.encodeBase64(aPet.getPic()); //轉成base64 byte陣列
				String base64Str = new String (base64,"UTF-8"); 
				baseStr.put(aPet.getPetId(), base64Str);
			}
		}
		if(arrPet.isEmpty()) return "redirect:/pet/petinfo.controller";

		m.addAttribute("arrPet",arrPet);
		m.addAttribute("baseStr",baseStr);
		return "PetInfo";
	}
	
	
	//開啟單一寵物詳細資訊(寵物資訊頁面用)
	@GetMapping("/detaildata/{petId}")
	public String processSingleDetail(@PathVariable("petId")int petId,Model m) throws UnsupportedEncodingException {
		System.out.println("petId="+petId);
		Pets thePet = pService.selectById(petId);
		byte[] base64 = Base64.encodeBase64(thePet.getPic()); //轉成base64 byte陣列
		String base64Str = new String (base64,"UTF-8"); 
		
		m.addAttribute("thePet",thePet);
		m.addAttribute("baseStr",base64Str);
		
		return "SinglePetInfo";
	}
	
	
	//開啟寵物單一資訊頁面(個人中心用)
	@GetMapping("/checkIfAfopted") @ResponseBody
	public int checkIfAdopted(@RequestParam("petId") int petId) {
		System.out.println("進入檢查環節");
		Pets check = pService.findByIdAndAdoptStatus(petId, "未領養");
		if(check==null) {
			return 0;
		}else {
			return 1;
		}
	}
	
}
