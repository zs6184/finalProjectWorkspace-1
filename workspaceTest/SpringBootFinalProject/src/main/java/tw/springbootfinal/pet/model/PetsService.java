package tw.springbootfinal.pet.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import tw.springbootfinal.mail.MailService;
import tw.springbootfinal.reservation.model.AdoptReservation;

@Service
@Transactional
public class PetsService {
	@Autowired
	private PetsRepository pRepo;

	@Autowired
	private MailService mService;

	
	//取得全部寵物資料
	public List<Pets> getAll(){
		return pRepo.findAll();
	}
	
	//取得全部未領養寵物資料
	public List<Pets> findNotAdopt(){
		return pRepo.findByAdoptStatus("未領養");
	}
	
	//使用性別與類別查詢
	public List<Pets> findBySexAndCategory(String sex ,String category){
		if(category==""&& sex!="") {
			return pRepo.findBySex(sex);
		}
		if(sex==""&&category!="") {
			return pRepo.findByCategory(category);
		}
		if(sex!=""&&category!="") {
			return pRepo.findBySexAndCategory(sex, category);
		}
		return null;
	}
	
	
	//新增單筆寵物資料(含相片)
	public void insertOne(Pets temp,MultipartFile pic) throws IOException {
		if(temp!=null) {
			byte[] picBytes = pic.getBytes();
			temp.setPic(picBytes);
			pRepo.save(temp);
		}
	}
	
	
	//使用id查詢資料
	public Pets selectById(int id) {
		return pRepo.getById(id);
	}
	
	
	//根據ID更新寵物資料
	public void updateOne(Pets temp,MultipartFile pic) throws IOException{

		Pets check =pRepo.findById(temp.getPetId()).get();
		check.setBean(temp);
		
		if(pic!=null && pic.isEmpty()==false) {
			byte[] picBytes = pic.getBytes();
			check.setPic(picBytes);
		}
		pRepo.save(check);
	}
	
	
	//根據ID刪除資料
	public void deleteById(int id) {
		pRepo.deleteById(id);
	}
	
	
	//根據ID與領養狀態搜尋
	public Pets findByIdAndAdoptStatus(Integer petId,String status) {
		return pRepo.findByPetIdAndAdoptStatus(petId, status);
	}
	
	
	
	//寄送通知EMAIL的方法(使用非同步執行)
	@Async
	public void processAlreadyAdoptedSendMail(List<AdoptReservation> theReserves,HttpServletRequest request) 
		throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
			//設置所需參數傳給EMAILService
			String templateNmae = "reservationMarker.html";
			String head = "通知:您預約領養的寵物已提前尋獲愛主。"; 
			boolean sendMail = mService.sendMailForList(request, theReserves, templateNmae, head) ;
			System.out.println("已送給MailService: "+sendMail);	
	}
	
}
