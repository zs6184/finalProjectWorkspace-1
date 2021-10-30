package tw.springbootfinal.users.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerBean, Integer> {
	//命名式: 相當於from UserProfiles where cusUsername = ?1
	public List<CustomerBean> findByCusUsername(String username); 
	public List<CustomerBean> findByPhoneNumber(String phoneNumber); 
	public List<CustomerBean> findByEmail(String Email);
	Optional<CustomerBean> getByCusUsername(String username);
	public Optional<CustomerBean> getByEmail(String email);
	public Optional<CustomerBean> getBySecretkey(String secretkey);
	

}
