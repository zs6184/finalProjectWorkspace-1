package tw.springbootfinal.booking.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author
 **/
@Service
@Transactional
public class BookingsServiceImpl implements BookingService {

    @Autowired
    private BookingsRepo bookingsRepo;

    @Override
    public BookingsBean getById(int id) {
        return bookingsRepo.findById(id).orElse(null);
    }

    @Override
    public List<BookingsBean> getAll() {
//        List<BookingsBean>list = bookingsRepo.findAll();
//        for(BookingsBean bean: list) {
//            bean.setBookingsTimeText(TimeEnum.getValueByCode(bean.getBookingsTime()));
//        }
//        return list;
        // 縮寫
        return bookingsRepo.findAll().stream().peek(item -> item.setBookingsTimeText(TimeEnum.getValueByCode(item.getBookingsTime()))).collect(Collectors.toList());
    }

    /**
     * 給ajax 傳物件的使用
     * @param bookingsDTO
     */
    @Override
    public void save(BookingsDTO bookingsDTO) {
        //TODO 有更好的mapstruct 但是這邊因為先簡單點所以不使用
//        List<BookingsBean>list = bookingsRepo.findByBookingsDateAndBookingsTime(bookingsDTO.getOrderDate(), bookingsDTO.getTime());
//        int sum = 0;
//        for(BookingsBean bean : list) {
//            sum += Integer.parseInt(bean.getBookingsNum());
//            // sum = sum + Integer.parseInt(bean.getBookingsNum());
//        }

        int sum = bookingsRepo.findByBookingsDateAndBookingsTime(bookingsDTO.getOrderDate(), bookingsDTO.getTime()).stream().mapToInt(item -> Integer.parseInt(item.getBookingsNum())).sum();
        if ((Integer.parseInt(bookingsDTO.getPeopleNum()) + sum ) > 20) throw new AjaxException("超過20人不可預約");

        BookingsBean bookingsBean = new BookingsBean();
        bookingsBean.setCusID(bookingsDTO.getCusid());
        bookingsBean.setCusRealName(bookingsDTO.getName());
        bookingsBean.setPhone(bookingsDTO.getPhone());
        bookingsBean.setBookingsNum(bookingsDTO.getPeopleNum());
        bookingsBean.setBookingsDate(bookingsDTO.getOrderDate());
        bookingsBean.setBookingsTime(bookingsDTO.getTime());
        bookingsBean.setKeepStatus("未到");

        save(bookingsBean);
    }

    @Override
    public void save(BookingsBean bookingsBean) {
        bookingsRepo.save(bookingsBean);
    }

    @Override
    public void delete(int id) {
        bookingsRepo.deleteById(id);
    }

    @Override
    public void update(BookingsBean bookingsBean) {

        // 可以增加是否可以更動
        bookingsRepo.save(bookingsBean);
    }



}
