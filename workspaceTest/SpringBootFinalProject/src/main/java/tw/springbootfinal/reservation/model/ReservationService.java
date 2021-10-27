package tw.springbootfinal.reservation.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationService {
	
	@Autowired
	private ReservationRepo rsRepo;
	
	//取得全部預約資料
	public List<AdoptReservation> findAll(){
		return rsRepo.findAll();
	}
	
	//根據複合主驗取得單筆資料
	public AdoptReservation selectOne(AdoptReservation ar) {
		return rsRepo.findBycusIdAndReserveTime(ar.getCusId(), ar.getReserveTime());
	}
	
	//新增一筆資料
	public AdoptReservation insertOrUpdate(AdoptReservation ar) {
		return rsRepo.save(ar);
	}
	
	//刪除一筆資料
	public void deleteOne(AdoptReservation ar) {
		rsRepo.deleteBycusIdAndReserveTime(ar.getCusId(), ar.getReserveTime());
	}
}
