package tw.springbootfinal.reservation.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<AdoptReservation, ReservationMultiKeyClass> {

}
