package tw.springbootfinal.booking.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepo extends JpaRepository<BookingsBean,Integer >{
	
	List<BookingsBean> findByBookingsDateAndBookingsTime(String date , String time);
}
