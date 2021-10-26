package tw.springbootfinal.booking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name= "Bookings")
public class BookingsBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "booking_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingsID;
	
	@Column(name="cus_ID")
	private Integer cusID;
	
	@Column(name="cus_realname")
	private String cusRealName;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="booking_num")
	private String bookingsNum;
	
	@Column(name="booking_time")
	private String bookingsTime;
	
	@Column(name="booking_date")
	private String bookingsDate;
	
	@Column(name="note")
	private String note;
	
	@Column(name="keep_status")
	private String keepStatus;
	
	@Column(name="emp_ID")
	private Integer empID;

	@Transient
	private String bookingsTimeText;

	public String getBookingsTimeText() {
		return bookingsTimeText;
	}

	public void setBookingsTimeText(String bookingsTimeText) {
		this.bookingsTimeText = bookingsTimeText;
	}

	public Integer getBookingID() {
		return bookingsID;
	}
	public void setBookingsID(Integer bookingsID) {
		this.bookingsID = bookingsID;
	}
	public Integer getCusID() {
		return cusID;
	}
	public void setCusID(Integer cusID) {
		this.cusID = cusID;
	}
	public String getCusRealName() {
		return cusRealName;
	}
	public void setCusRealName(String cusRealName) {
		this.cusRealName = cusRealName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBookingsNum() {
		return bookingsNum;
	}
	public void setBookingsNum(String bookingsNum) {
		this.bookingsNum = bookingsNum;
	}
	public String getBookingsTime() {
		return bookingsTime;
	}
	public void setBookingsTime(String bookingsTime) {
		this.bookingsTime = bookingsTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getKeepStatus() {
		return keepStatus;
	}
	public void setKeepStatus(String keepStatus) {
		this.keepStatus = keepStatus;
	}
	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}


	public String getBookingsDate() {
		return bookingsDate;
	}
	public void setBookingsDate(String bookingsDate) {
		this.bookingsDate = bookingsDate;
	}
	public Integer getBookingsID() {
		return bookingsID;
	}
	@Override
	public String toString() {
		return "BookingsBean [bookingsID=" + bookingsID + ", cusID=" + cusID + ", cusRealName=" + cusRealName
				+ ", phone=" + phone + ", bookingsNum=" + bookingsNum + ", bookingsTime=" + bookingsTime
				+ ", bookingsDate=" + bookingsDate + ", note=" + note + ", keepStatus=" + keepStatus + ", empID="
				+ empID + "]";
	}
	
	
	public BookingsBean(Integer bookingsID, Integer cusID, String cusRealName, String phone, String bookingsNum,
			String bookingsTime, String bookingsDate, String note, String keepStatus, Integer empID) {
		super();
		this.bookingsID = bookingsID;
		this.cusID = cusID;
		this.cusRealName = cusRealName;
		this.phone = phone;
		this.bookingsNum = bookingsNum;
		this.bookingsTime = bookingsTime;
		this.bookingsDate = bookingsDate;
		this.note = note;
		this.keepStatus = keepStatus;
		this.empID = empID;
	}
	public BookingsBean() {
		
	}

	
	public BookingsBean setBean(BookingsBean temp) {
		this.setCusID(temp.getCusID());
		this.setCusRealName(temp.getCusRealName());
		this.setPhone(temp.getPhone());
		this.setBookingsNum(temp.getBookingsNum());
		this.setBookingsTime(temp.getBookingsTime());
		this.setBookingsDate(temp.getBookingsDate());
		this.setNote(temp.getNote());
		this.setKeepStatus(temp.getKeepStatus());
		this.setEmpID(temp.getEmpID());
		
		
		return this;
	}
	
}
