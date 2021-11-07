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
	
	//根據複合主驗取得單筆資料(使用Bean物件承接)
	public AdoptReservation selectOne(AdoptReservation ar) {
		return rsRepo.findBycusIdAndReserveTime(ar.getCusId(), ar.getReserveTime());
	}
	
	//根據複合主驗取得單筆資料(單獨給出複合主鍵的變數)
	public AdoptReservation selectByMultiKey(Integer id, String time) {
		return rsRepo.findBycusIdAndReserveTime(id, time);
	}
	
	//新增一筆資料
	public AdoptReservation insertOne(AdoptReservation ar) {
		return rsRepo.save(ar);
	}
	
	//修改一筆資料
	public AdoptReservation updateOne(AdoptReservation ar) {
		return rsRepo.save(ar);
	}
	
	//刪除一筆資料
	public void deleteOne(AdoptReservation ar) {
		rsRepo.deleteBycusIdAndReserveTime(ar.getCusId(), ar.getReserveTime());
	}
	
	//使用使用者ID查詢個人紀錄給會員中心
	public List<AdoptReservation> selectTheCusRec(Integer id) {
		return rsRepo.findByCusId(id);
	}
	
	//使用寵物ID找到所有對應的預約紀錄
	public List<AdoptReservation> findByPetIdAndKeepStatus(Integer petId,String status){
		return rsRepo.findByPetIdAndKeepStatus(petId,status);
	}
	
	//刪除特定寵物ID下的預約紀錄
	public void deleteByPetId(Integer petId) {
		rsRepo.deleteByPetId(petId);
	}
}
