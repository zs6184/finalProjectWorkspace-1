package tw.springbootfinal.products.model;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductsRepo prodRepo;
	
	//取得全部產品資料
	public List<Products> findAll() {
		return prodRepo.findAll();
	}
	
	//ID查詢單筆
	public Products selectById(Integer id) {
		return prodRepo.getById(id);
	}
	
	//新增單筆
	public void insertOne(Products temp,MultipartFile pic) throws IOException {
		if(temp!=null) {
			byte[] picBytes = pic.getBytes();
			temp.setPic(picBytes);
			prodRepo.save(temp);
		}
	}
	
	//使用ID刪除單筆
	public void deleteById(int id) {
		prodRepo.deleteById(id);
	}
	
	//修改單筆資料
	public void updateOne(Products temp,MultipartFile pic) throws IOException {
		Products check = prodRepo.findById(temp.getProductID()).get();
		check.setBean(temp);
		if(pic!=null && pic.isEmpty()==false) {
			System.out.println("not null");
			byte[] picBytes = pic.getBytes();
			check.setPic(picBytes);
		}
		prodRepo.save(check);
	}
	
}
