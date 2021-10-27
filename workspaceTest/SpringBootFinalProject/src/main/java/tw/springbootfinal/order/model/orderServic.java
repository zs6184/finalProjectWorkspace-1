package tw.springbootfinal.order.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class orderServic {
	@Autowired
	private orderRepository oRepository;
	
	//儲存一筆訂單
	public orderBean saveOrder(orderBean orderbean) {
		return oRepository.save(orderbean);
	}
	
	

}
