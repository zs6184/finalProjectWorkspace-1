package tw.springbootfinal.order.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface orderRepository extends JpaRepository<orderBean, Integer> {

	@Query(value = "SELECT top(1)* FROM Orders where cus_ID = ?1 order by order_time desc",nativeQuery = true)
	public orderBean latestOrder(int id);
	
	public List<orderBean> findByOrderstatus(String state);
	
	//訂單編號，顧客帳號，顧客電話，訂單日期
	@Query(value = "SELECT * FROM Orders o LEFT JOIN Customers c ON o.cus_ID=c.cus_ID where o.order_ID like  %?1% or c.cus_username like %?1% or c.phone like %?1% or convert(varchar,o.order_time,120) like %?1%" ,nativeQuery = true)
	public List<orderBean> searchorder(String str);
	
	//搜尋顧客訂單編號，顧客帳號，顧客電話，訂單日期
	@Query(value = "SELECT * FROM Orders o WHERE  o.order_ID like  '%25%' or convert(varchar,o.order_time,120) like '%25%' AND o.cus_ID in(select c.cus_ID from Customers c where c.cus_ID= 2 )" ,nativeQuery = true)
	public List<orderBean> searchcusorder(Integer id,String str);
	
}
