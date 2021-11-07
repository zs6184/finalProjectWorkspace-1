package tw.springbootfinal.pet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import freemarker.template.TemplateException;
import tw.springbootfinal.pet.model.Pets;
import tw.springbootfinal.pet.model.PetsService;
import tw.springbootfinal.reservation.model.AdoptReservation;
import tw.springbootfinal.reservation.model.ReservationService;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
@RequestMapping("/Backstage/pet")
@SessionAttributes(names = {"sexSet","cateSet"})
public class BackPetsController {
		
	@Autowired
	private PetsService pService;
	@Autowired
	private CustomerService cService;
	@Autowired
	private ReservationService rService;
//--------------------------------------------------------------
	
	//取得所有寵物資料
	@GetMapping("/backpetinfo.controller")
	public String processGetAll(@SessionAttribute("username")String username,HttpServletRequest request,Model m) {
		List<Pets>arrPet = pService.getAll();
		Set<String> sexSet = new HashSet<String>();
		Set<String> cateSet = new HashSet<String>();
		for(Pets aPet:arrPet) {
			sexSet.add(aPet.getSex());		//用Set將重複值篩選掉
			cateSet.add(aPet.getCategory());//用Set將重複值篩選掉
		}
		m.addAttribute("arrPet",arrPet);
		m.addAttribute("cateSet",cateSet);
		m.addAttribute("sexSet",sexSet);
		//會員圖片
		String imageName = cService.selectImage(username, request);
		m.addAttribute("imageName",imageName);

		return  "BackPetInfo";
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
	
	//AJAX取得所有未領養寵物資料後續填值用
	@GetMapping("/getAllPetAjax")
	public void processGetAllPet(HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		List<Pets> allPets = pService.findNotAdopt();
		JSONObject petData = new JSONObject();
		petData.put("petData", allPets);
		PrintWriter out = response.getWriter();
		out.println(petData);
		out.close();
	}
	
	//使用條件進行查詢
	@PostMapping("/searchdata.controller")
	public String processSearchData(Pets pet,Model m){
		String sex = pet.getSex();
		String category = pet.getCategory();
		List<Pets> arrPet = pService.findBySexAndCategory(sex, category);
		
		if(arrPet==null||arrPet.isEmpty()) {
			return "redirect:/Backstage/pet/backpetinfo.controller";
		}
		
		m.addAttribute("arrPet",arrPet);
		
		return "BackPetInfo";
	}
	
	//新增單一資料
	@PostMapping("/insertPetInfo.controller")
	public String processAddPetInfo(Pets temp,@RequestParam("mypic") MultipartFile pic) throws IOException {
		String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 寫出所有null後將所有空白轉null
		Pets transfer = JSON.parseObject(jsonStr,Pets.class);
		
		pService.insertOne(transfer, pic);
		
		return "redirect:/Backstage/pet/backpetinfo.controller";
	}
	
	//選擇並查看寵物個別資料
	@GetMapping("/selectbyid.controller")
	public void processSelectById(@RequestParam int id,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		Pets temp = pService.selectById(id);
		//資料轉JSON物件
		String petStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue));
		JSONObject petData = JSONObject.parseObject(petStr);

		PrintWriter out = response.getWriter();
		out.print(petData);
		out.close();
		System.out.println("輸出完成");
	}
	
	//更新寵物資料
	@PostMapping("/updateone.controller")
	public String processUpdateOne(Pets temp,@RequestParam("mypic") MultipartFile pic,
			HttpServletRequest request) throws IOException, TemplateException {
		//若是更改為已領養情況，刪除該寵物的未領養預約並寄信
		if(temp.getAdoptStatus().equals("已領養")) {
			Integer thePet = temp.getPetId();
			List<AdoptReservation> theReserves = rService.findByPetIdAndKeepStatus(thePet,"未赴約");
			pService.processAlreadyAdoptedForgotPasswordSendMail(theReserves,request);
			rService.deleteByPetId(thePet);
		}
			//執行寵物資料更新
			String jsonStr = (JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue)).replaceAll("\"\"","null"); // 將所有空白轉為null
			Pets transfer = JSON.parseObject(jsonStr, Pets.class);
			pService.updateOne(transfer, pic);
			System.out.println("修改完成");

		return "redirect:/Backstage/pet/backpetinfo.controller";
	}
	
	//刪除寵物資料
	@GetMapping("/deletebyid.controller")
	public String processDeleteById(@RequestParam int id) {
		pService.deleteById(id);
		
		return "BackPetInfo";
	}
		
}
