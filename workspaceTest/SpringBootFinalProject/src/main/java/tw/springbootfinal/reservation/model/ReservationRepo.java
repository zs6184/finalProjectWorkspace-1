package tw.springbootfinal.reservation.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<AdoptReservation, ReservationMultiKeyClass> {
	
	//使用複合主鍵查詢
	public AdoptReservation findBycusIdAndReserveTime(Integer cusId,String reserveTime);
	
	//使用複合主鍵刪除
	public void deleteBycusIdAndReserveTime(Integer cusId,String reserveTime);
	
	//使用會員ID查詢所有個人紀錄
	public List<AdoptReservation> findByCusId(Integer cusId);
	
	//使用寵物Id刪除
	public void deleteByPetId(Integer petId);
	
	//使用寵物ID尋找預約紀錄
	public List<AdoptReservation> findByPetIdAndKeepStatus(Integer petId, String status);
}
