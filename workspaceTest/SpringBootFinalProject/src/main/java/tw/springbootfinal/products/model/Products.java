package tw.springbootfinal.products.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_ID")
	private Integer productID;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="unitprice")
	private int unitprice;
	
	@Column(name="category")
	private String category;
	
	@Column(name="total_instore")
	private int totalInstore;
	
	@Column(name="total_inorder")
	private int totalInorder;
	
	@Column(name="note")
	private String note;
	
	@Column(name="pic")
	private byte[] pic;

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productId) {
		this.productID = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(int unitprice) {
		this.unitprice = unitprice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getTotalInstore() {
		return totalInstore;
	}

	public void setTotalInstore(int totalInstore) {
		this.totalInstore = totalInstore;
	}

	public int getTotalInorder() {
		return totalInorder;
	}

	public void setTotalInorder(int totalInorder) {
		this.totalInorder = totalInorder;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
	public void setBean(Products temp) {
		this.productID=temp.getProductID();
		this.productName=temp.getProductName();
		this.category=temp.getCategory();
		this.totalInstore=temp.getTotalInstore();
		this.unitprice=temp.getUnitprice();
		this.note=temp.getNote();
	}
}
