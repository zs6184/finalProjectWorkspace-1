package tw.springbootfinal.announcements.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.springbootfinal.announcements.model.Announcements;
import tw.springbootfinal.announcements.model.AnnouncementsService;
import tw.springbootfinal.pet.model.Pets;

@Controller
@RequestMapping("/")
public class IndexAnnounceController {

	@Autowired
	private AnnouncementsService aService;
	
	/*
	 * 把所有資料送到前台首頁
	 */
	@GetMapping("/indexAnn.controller")
	public String processLoadingPage(Model m) throws UnsupportedEncodingException {
		List<Announcements>arrAnnounce = aService.getAll();
		Map<Integer,String> baseStr = new HashMap<>();
		for(Announcements aAnn:arrAnnounce) {
			byte[] base64 = Base64.encodeBase64(aAnn.getPic());
			String base64Str = new String (base64,"UTF-8");
			baseStr.put(aAnn.getAnnounceID(), base64Str);
			
		}
		m.addAttribute("arrAnnounce",arrAnnounce);
		m.addAttribute("baseStr",baseStr);
		
		return "IndexAnnounce";
	}
	
	
}
