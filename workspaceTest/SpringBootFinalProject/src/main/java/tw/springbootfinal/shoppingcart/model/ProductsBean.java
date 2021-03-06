package tw.springbootfinal.shoppingcart.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

import tw.springbootfinal.order.model.orderDetailsBean;

@Entity
@Table(name="Products")
@Component
public class ProductsBean {
	
	@Id
	@JSONField(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_ID")
	private int id;
	@Column(name = "product_name")
	private String name;
	@Column(name = "unitprice")
	private int price;
	@Column(name = "category")
	private String category;
	@Column(name = "total_instore")
	private int instore;
	@Column(name = "total_inorder")
	private int inorder;
	
	
	@JSONField(name = "num")
	@Transient
	private int num;
	
	@OneToMany(mappedBy = "product")
	private Set<orderDetailsBean> orderDetails =new  LinkedHashSet<orderDetailsBean>();
	
	
	
	public Set<orderDetailsBean> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<orderDetailsBean> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getnum() {
		return  num;
	}

	public void setnum(int  num) {
		this.num =  num;
	}


	
	private byte[] pic;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getInstore() {
		return instore;
	}

	public void setInstore(int instore) {
		this.instore = instore;
	}

	public int getInorder() {
		return inorder;
	}

	public void setInorder(int inorder) {
		this.inorder = inorder;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
}