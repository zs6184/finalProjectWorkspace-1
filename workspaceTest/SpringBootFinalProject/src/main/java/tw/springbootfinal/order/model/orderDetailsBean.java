package tw.springbootfinal.order.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import tw.springbootfinal.shoppingcart.model.ProductsBean;

@Entity
@Table(name = "Order_Details")
@Component
public class orderDetailsBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public orderDetailsBean(){
		
	}
	
	@Id
	@ManyToOne
	@JoinColumn(name = "product_ID")
	private  ProductsBean product;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "order_ID")
	private orderBean order;
	
	@Column(name = "num")
	private int num;
	@Column(name = "unitprice")
	private int unitprice;
	@Column(name = "subtotal")
	private int subtotal;

	public ProductsBean getProduct() {
		return product;
	}
	public void setProduct(ProductsBean product) {
		this.product = product;
	}
	public orderBean getOrder() {
		return order;
	}
	public void setOrder(orderBean order) {
		this.order = order;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(int unitprice) {
		this.unitprice = unitprice;
	}
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

}
