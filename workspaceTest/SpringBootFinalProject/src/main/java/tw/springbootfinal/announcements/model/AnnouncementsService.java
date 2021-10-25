package tw.springbootfinal.announcements.model;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class AnnouncementsService {
	@Autowired
	private AnnouncementsRepository aRepo;
	
	/*
	 * 取得全部的資料
	 */
	public List<Announcements> getAll(){
		return aRepo.findAll();
	}
	
	
	/*
	 * 新增一筆資料
	 */
	public void insertOne(Announcements temp,MultipartFile pic) throws IOException {
		if(temp != null) {
			//得到二進制數據
			byte[] picBytes = pic.getBytes();
			temp.setPic(picBytes);
			aRepo.save(temp);
		}
	}
	
	/*
	 * 使用id查詢資料
	 */
	
	public Announcements selectById(int id) {
		return aRepo.getById(id);
	}
	
	/*
	 * 根據id更改資料
	 */
	
	public void updateOne(Announcements temp , MultipartFile pic) throws IOException {
		int id = temp.getAnnounceID();
		Announcements check = aRepo.findById(id).get();
		check.setBean(temp);
		if(pic!=null && pic.isEmpty()==false) {
			byte[] picBytes = pic.getBytes();
			check.setPic(picBytes);
			check.setPic(picBytes);
		}
		aRepo.save(check);
	}
	/*
	 * 根據id刪除資料
	 */
	
	public void deleteById(int id) {
		aRepo.deleteById(id);
	}
	
}
