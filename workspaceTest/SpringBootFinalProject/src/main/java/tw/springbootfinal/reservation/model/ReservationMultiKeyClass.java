package tw.springbootfinal.reservation.model;

import java.io.Serializable;

//複合主鍵類
public class ReservationMultiKeyClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer cusId;
	private String reserveTime;

	// Constructor
	public ReservationMultiKeyClass() {
	}

	public ReservationMultiKeyClass(int cusId, String reserveTime) {
		this.cusId = cusId;
		this.reserveTime = reserveTime;
	}

	// Getter and Setter
	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}

	// Override hashCode() and equals() 
	@Override
	public int hashCode() { // 執行hashCode判斷用
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((cusId == null) ? 0 : cusId.hashCode());
		result = PRIME * result + ((reserveTime == null) ? 0 : reserveTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		final ReservationMultiKeyClass other = (ReservationMultiKeyClass) obj;
		if (cusId == null) {
			if (other.cusId != null) {
				return false;
			}
		} else if (!cusId.equals(other.cusId)) {
			return false;
		}

		if (reserveTime == null) {
			if (other.reserveTime != null) {
				return false;
			}
		} else if (!reserveTime.equals(other.reserveTime)) {
			return false;
		}

		return true;
	}

}
