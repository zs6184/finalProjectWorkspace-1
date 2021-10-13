package tw.finalspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.finalspring.model.BookingsBean;
import tw.finalspring.model.BookingsDAO;

@Service
@Transactional
public class BookingsService {

	@Autowired
	private BookingsDAO bookingsDAO;
	
	//插入單筆
	public BookingsBean insertOne(BookingsBean temp) {
		return bookingsDAO.insertOne(temp);
	}
	
	
	//載入全部
	public List<BookingsBean> selectAll(){
		System.out.println("Service do selectALL");
		return bookingsDAO.selectAll();
	}
	
	//使用ID單筆查詢
	public BookingsBean selectById(int bookingsID) {
		return bookingsDAO.selectById(bookingsID);
	}
	
	//使用ID單筆刪除
	public boolean deleteById(int bookingsID) {
		return bookingsDAO.deleteById(bookingsID);
	}
	
	//更新單筆資料
	public BookingsBean updateOne(int bookingsID ,BookingsBean temp) {
		return bookingsDAO.updateOne(bookingsID,temp);
	}
	
}
