package tw.springbootfinal.booking.model;

import java.util.List;

public interface BookingService {
	BookingsBean getById(int id);
	List<BookingsBean> getAll();
	
	/**
	 * 避免model直接對外使用 , 使用DTO遮擋
	 */
	
	void save(BookingsDTO bookingsDTO);
	void save(BookingsBean bookingsBean);
	void delete(int id);
	void update(BookingsBean bookingsBean);
	
}
