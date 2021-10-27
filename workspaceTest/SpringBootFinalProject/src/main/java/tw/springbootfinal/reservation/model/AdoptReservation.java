package tw.springbootfinal.reservation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "Adopt_Reservation")
@IdClass(ReservationMultiKeyClass.class)
public class AdoptReservation implements Serializable {

	private Integer cusId;
	private String reserveTime;
	private Integer petId;
	private String petName;
	private String cusRealname;
	private String phone;
	private String keepStatus;

	@Id
	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	@Id
	public String getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getCusRealname() {
		return cusRealname;
	}

	public void setCusRealname(String cusRealname) {
		this.cusRealname = cusRealname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getKeepStatus() {
		return keepStatus;
	}

	public void setKeepStatus(String keepStatus) {
		this.keepStatus = keepStatus;
	}

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	// ToString
	@Override
	public String toString() {
		return "AdoptReservation [cusId=" + cusId + ", reserveTime=" + reserveTime + ", petName=" + petName
				+ ", cusRealname=" + cusRealname + ", phone=" + phone + ", keepStatus=" + keepStatus + "]";
	}

}
