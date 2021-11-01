package tw.springbootfinal.users.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tw.springbootfinal.users.util.UserAuthorityUtils;

@Service
public class AuthUserDetailService implements UserDetailsService {

	@Autowired
	private CustomerService cusService;
	
	//進行帳號密碼驗證
	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		CustomerBean cusBean = cusService.getByCusUsername(username);
		
		UserAuthorityUtils userAuthority = new UserAuthorityUtils();
		System.out.println("cusRole: "+userAuthority.createAuthorities(cusBean));
		
		
		return new User(cusBean.getCusUsername(), cusBean.getCusPassword(), userAuthority.createAuthorities(cusBean));
	}

}
