package iii.boothomework.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CustomerService {
	@Autowired
	private CustomerRepository cRepo;
	
	//登入時用cusUsername抓資料
	public Optional<Customers> getByUsername(String username) {
		Optional<Customers> op1 = cRepo.findByUsername(username);
		return op1;
	}
	
	//進行帳號新增
	public void createUsers(Customers user) {
		cRepo.save(user);
	}
	
	//進行帳號更新
	public Customers updateUsers(Customers user) {
		return cRepo.save(user);
	}
	
	//使用Id查詢
	public Customers findById(int id){
		return cRepo.getById(id);
	}
	
	//使用Id刪除
	public void deleteById(int id) {
		cRepo.deleteById(id);
	}
}
