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
	
	//新增一筆資料
	public AdoptReservation insertOne(AdoptReservation ar) {
		return rsRepo.save(ar);
	}
}
