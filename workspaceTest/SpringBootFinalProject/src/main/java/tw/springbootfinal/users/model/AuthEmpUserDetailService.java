//package tw.springbootfinal.users.model;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import tw.springbootfinal.users.util.EmpUserAuthorityUtils;
//
//@Service
//public class AuthEmpUserDetailService implements UserDetailsService {
//
//	@Autowired
//	private EmployeeService empService;
//	
//	//進行帳號密碼驗證
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		EmployeeBean empBean = empService.getByEmpUsername(username);
//		
//		EmpUserAuthorityUtils empUserAuthority = new EmpUserAuthorityUtils();
//		System.out.println("empRole: "+empUserAuthority.createAuthorities(empBean));
//		
//		//User user = new User(cusBean.getCusUsername(), cusBean.getCusPassword(), userAuthority.createAuthorities(cusBean));
//		
////		Collections.emptyList()
//		return new User(empBean.getEmpUsername(), empBean.getEmpPassword(), empUserAuthority.createAuthorities(empBean));
//	}
//
//}
