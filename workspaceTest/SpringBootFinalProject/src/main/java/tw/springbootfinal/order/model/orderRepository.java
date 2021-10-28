package tw.springbootfinal.order.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface orderRepository extends JpaRepository<orderBean, Integer> {

	@Query(value = "SELECT top(1)* FROM Orders where cus_ID = ?1 order by order_time desc",nativeQuery = true)
	public orderBean latestOrder(int id);
}
