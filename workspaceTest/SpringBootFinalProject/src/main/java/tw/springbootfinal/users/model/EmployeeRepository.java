package tw.springbootfinal.users.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<EmployeeBean, Integer> {
	
	//查詢empUsername的最大值資料
	@Query(value = "from EmployeeBean as e where e.empUsername = (select max(eBD.empUsername) from EmployeeBean as eBD)")
	public List<EmployeeBean> findMaxEmpUsername();
	public List<EmployeeBean> findByPhoneNumber(String phoneNumber);
	public List<EmployeeBean> findByEmpUsername(String empUsername);
	public List<EmployeeBean> findByEmail(String email);
	public List<EmployeeBean> findBySecretkey(String secretkey);
	public Optional<EmployeeBean> getByEmpUsername(String empUsername);
	public Optional<EmployeeBean> getBySecretkey(String secretkey);

	

}
