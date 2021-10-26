package tw.springbootfinal.Coupons.model;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import tw.springbootfinal.order.model.orderBean;

@Entity
@Table(name = "[Coupons]")
@Component
public class Coupons {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_ID")
	private int couponId;
	
	@OneToMany(mappedBy = "coupons")
	private Set<orderBean> orderBean =new LinkedHashSet<orderBean>();
	
	@Override
	public String toString() {
		return "Coupons [couponId=" + couponId + ", couponName=" + couponName + ", couponCode=" + couponCode
				+ ", couponDiscount=" + couponDiscount + ", couponDue=" + couponDue + "]";
	}
	
	@Column(name = "coupon_name")
	private String couponName;
	
	@Column(name = "coupon_code")
	private int couponCode;
	
	@Column(name = "[discount]")
	private int	couponDiscount;
	
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
