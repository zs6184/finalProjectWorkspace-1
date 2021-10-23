package tw.finalspring.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "[Coupons]")
@Component
public class Coupons {
	//Àu´f½XID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_ID")
	private int couponId;
	
	@Override
	public String toString() {
		return "Coupons [couponId=" + couponId + ", couponName=" + couponName + ", couponCode=" + couponCode
				+ ", couponDiscount=" + couponDiscount + ", couponDue=" + couponDue + "]";
	}
	//Àu´f½X¦WºÙ
	@Column(name = "coupon_name")
	private String couponName;
	//Àu´f½X
	@Column(name = "coupon_code")
	private int couponCode;
	//Àu´f½X§é»ùª÷ÃB
	@Column(name = "[discount]")
	private int	couponDiscount;
	//Àu´f½X¹L´Á¤é
	@Column(name = "due_date")
	private Date couponDue;
	
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public int getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(int couponCode) {
		this.couponCode = couponCode;
	}
	public int getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(int couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public Date getCouponDue() {
		return couponDue;
	}
	public void setCouponDue(Date couponDue) {
		this.couponDue = couponDue;
	}
	

}
