package tw.springbootfinal.reservation.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Adopt_Reservation")
@Component
public class AdoptReservation implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int cusId;
	private String petName;
	private String cusRealname;
	@Id
	private LocalDateTime reserveTime;
	private String phone;
	private String keepStatus;

	
	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
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

	public LocalDateTime getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(LocalDateTime reserveTime) {
		this.reserveTime = reserveTime;
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

}
