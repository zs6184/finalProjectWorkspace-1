package tw.finalspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.finalspring.model.AnnouncementsBean;
import tw.finalspring.model.AnnouncementsDAO;

@Service
@Transactional
public class AnnouncementsService {

	@Autowired
	private AnnouncementsDAO announcementsDAO;
	
	//插入單筆
	public AnnouncementsBean insertOne(AnnouncementsBean temp) {
		return announcementsDAO.insertOne(temp);
	}
	
	
	//載入全部
	public List<AnnouncementsBean> selectAll(){
		System.out.println("Service do selectALL");
		return announcementsDAO.selectAll();
	}
	
	//使用ID單筆查詢
	public AnnouncementsBean selectById(int announceID) {
		return announcementsDAO.selectById(announceID);
	}
	
	//使用ID單筆刪除
	public boolean deleteById(int announceID) {
		return announcementsDAO.deleteById(announceID);
	}
	
	//更新單筆資料
	public AnnouncementsBean updateOne(int announceID ,AnnouncementsBean temp) {
		return announcementsDAO.updateOne(announceID,temp);
	}
	
}
