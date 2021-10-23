package tw.springbootfinal.shoppingcart.model;

import com.alibaba.fastjson.annotation.JSONField;

public class ShopCartBean {
	
	@JSONField(name = "id")
	private int productId;
	
	@JSONField(name = "num")
	private int quantity;
	
	private int customerId;
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
