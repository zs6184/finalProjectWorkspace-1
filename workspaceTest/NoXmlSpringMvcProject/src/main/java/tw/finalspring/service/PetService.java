package tw.finalspring.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tw.finalspring.model.PetBean;
import tw.finalspring.model.PetDAO;

@Service
@Transactional
public class PetService {

	@Autowired
	private PetDAO petDAO;
	
	//插入單筆
	public PetBean insertOne(PetBean temp,MultipartFile pic) throws IOException {
		return petDAO.insertOne(temp,pic);
	}
	
	//載入全部
	public List<PetBean> selectAll(){
		return petDAO.selectAll();
	}
	
	//載入全部未領養
	public List<PetBean> selectNoAdopt(){
		return petDAO.selectNoAdopt();
	}
	
	//使用ID單筆查詢
	public PetBean selectById(int petId) {
		return petDAO.selectById(petId);
	}
	
	//使用ID單筆刪除
	public void deleteById(int petId) {
		petDAO.deleteById(petId);
	}
	
	//更新單筆資料
	public PetBean updateOne(int petId ,PetBean temp,MultipartFile pic) throws IOException {
		return petDAO.updateOne(petId,temp,pic);
	}
	
	//根據類別與性別進行查詢
	public List<PetBean> searchData(String category,String sex) {
		return petDAO.searchData(category,sex);
	}
	
}
