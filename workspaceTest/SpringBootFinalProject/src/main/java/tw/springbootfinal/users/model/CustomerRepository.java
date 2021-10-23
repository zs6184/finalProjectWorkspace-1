package tw.springbootfinal.users.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerBean, Integer> {
	//命名式: 相當於from UserProfiles where cusUsername = ?1
	public List<CustomerBean> findByCusUsername(String username); 
	public List<CustomerBean> findByPhoneNumber(String phoneNumber); 
	public List<CustomerBean> findByEmail(String Email);
	

}
