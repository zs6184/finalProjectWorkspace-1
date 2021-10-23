package tw.springbootfinal.shoppingcart.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ShopCartService {
	@Autowired
	ProductsRepostiory pRepostiory;
	
	//使用id尋找項商品
	public ProductsBean selecyProductbyId(int id){
		return pRepostiory.findById(id).get();
	}
	//

}
