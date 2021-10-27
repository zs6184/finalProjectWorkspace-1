package tw.springbootfinal.booking.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author
 **/
@Repository
public interface BookingsRepo extends JpaRepository<BookingsBean, Integer> {

    List<BookingsBean> findByBookingsDateAndBookingsTime(String date, String time);
}
