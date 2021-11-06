package tw.springbootfinal.booking.model;

import java.util.List;

import tw.springbootfinal.reservation.model.AdoptReservation;

public interface BookingService {
    BookingsBean getById(int id);
    List<BookingsBean> getAll();
    //回傳使用者ID給個人紀錄頁面
    List<BookingsBean> selectTheCusRec(Integer id);
    
    
    // 此為避免model直接對外使用
    void save(BookingsDTO bookingsDTO);
    void save(BookingsBean bookingsBean);
    void delete (int id);
    void update(BookingsBean bookingsBean);
}
