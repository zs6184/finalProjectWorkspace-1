package tw.springbootfinal.order.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface orderRepository extends JpaRepository<orderBean, Integer> {

}
