package tw.springbootfinal.order.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.springbootfinal.users.model.CustomerBean;

@Service
@Transactional
public class orderServic {
	@Autowired
	private orderRepository oRepository;
	
	//儲存一筆訂單
	public orderBean saveOrder(orderBean orderbean) {
		return oRepository.save(orderbean);
	}
	
	//搜尋使用者最近一筆訂單
	public orderBean latestOrder(CustomerBean c) {
		return oRepository.latestOrder(c.getCusId());
	}
	
	
	

}
