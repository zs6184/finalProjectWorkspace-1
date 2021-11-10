package tw.springbootfinal.order.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.springbootfinal.users.model.CustomerBean;

@Service
@Transactional
public class orderServic {
	@Autowired
	private orderRepository oRepository;
	
	//透過id尋找訂單
	public orderBean selectbyid(int id) {
		return oRepository.findById(id).get();
	}
	
	// 儲存一筆訂單
	public orderBean saveOrder(orderBean orderbean) {
		return oRepository.save(orderbean);
	}

	// 搜尋使用者最近一筆訂單
	public orderBean latestOrder(CustomerBean c) {
		return oRepository.latestOrder(c.getCusId());
	}

	// 尋找訂單狀態
	public List<orderBean> findByOrderstatus(String state) {
		return oRepository.findByOrderstatus(state);
	}
	
	//後台搜尋訂單
	public List<orderBean> backsearchOrder(String str) {
		return oRepository.searchorder(str);
	}
	
	//單一會員訂單搜尋
	public List<orderBean> searchcusorder(Integer  id,String str) {
		return oRepository.searchcusorder(id,str);
	}
	
	//尋找使用者訂單
	public List<orderBean>findByOrdercus(int id){
		
		List<orderBean> findByOrdercus = oRepository.findByOrdercus(id);
		System.out.println("我是訂單時間 測試:"+findByOrdercus.get(0).getOrdertime());
		return findByOrdercus;
	}
	

}
