package tw.springbootfinal.order.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonFormat;

import tw.springbootfinal.Coupons.model.Coupons;
import tw.springbootfinal.users.model.CustomerBean;

@Entity
@Table(name = "Orders")
@Component
public class orderBean {
	
	
	
	@Id
	@Column(name = "order_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="pay_method")
	private String paymethod;
	
	@Column(name="order_status")
	private String orderstatus;
	
	@Column(name="pay_status")
	private int paystatus;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name="order_time")
	private Timestamp ordertime;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name="pickup_time")
	private Timestamp pickuptime;
	
	@Column(name = "total")
	private int total;
	
	@Column(name="pickup_method")
	private String pickupmethod;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "merchant_trade_no")
	private String merchantTradeNo;


	public String getMerchantTradeNo() {
		return merchantTradeNo;
	}

	public void setMerchantTradeNo(String merchantTradeNo) {
		this.merchantTradeNo = merchantTradeNo;
	}

	@ManyToOne
	@JoinColumn(name = "cus_ID")
	private CustomerBean customer;
	
	@ManyToOne
	@JoinColumn(name = "coupon_ID")
	private Coupons coupons;
	
	@OneToMany(mappedBy = "order",cascade =CascadeType.ALL)
	private Set<orderDetailsBean> orderDetails =new LinkedHashSet<orderDetailsBean>();
	

	public Set<orderDetailsBean> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<orderDetailsBean> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public int getCusid() {
//		return cusid;
//	}
//
//	public void setCusid(int cusid) {
//		this.cusid = cusid;
//	}

	public Coupons getCoupons() {
		return coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public int getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(int paystatus) {
		this.paystatus = paystatus;
	}

	public Timestamp getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}

	public Timestamp getPickuptime() {
		return pickuptime;
	}

	public void setPickuptime(Timestamp pickuptime) {
		this.pickuptime = pickuptime;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getPickupmethod() {
		return pickupmethod;
	}

	public void setPickupmethod(String pickupmethod) {
		this.pickupmethod = pickupmethod;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	
	
}
