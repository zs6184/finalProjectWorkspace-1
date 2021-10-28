package tw.springbootfinal.users.util;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import tw.springbootfinal.users.model.EmployeeBean;

public class EmpUserAuthorityUtils {

	// 利用Spring提供的AuthorityUtils中createAuthorityList將該群組加入相關roles
	// 以便用一個List變數就儲存所有roles
	private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN",
			"ROLE_EMPLOYEE", "ROLE_MEMBER");

	private static final List<GrantedAuthority> EMPLOYEE_ROLES = AuthorityUtils.createAuthorityList("ROLE_MEMBER",
			"ROLE_EMPLOYEE");

	private static final List<GrantedAuthority> MEMBER_ROLES = AuthorityUtils.createAuthorityList("ROLE_MEMBER");
	
	public  Collection<? extends GrantedAuthority> createAuthorities(EmployeeBean empBean) {
		String role = empBean.getRole();
		if (role.contains("ADMIN")) { // role中含有admin，即有管理者之權限
			return ADMIN_ROLES;
		} else if (role.contains("EMPLOYEE")) {// role中含有employee，即有後台之權限
			return EMPLOYEE_ROLES;
		}
		return MEMBER_ROLES; // 否則則為一般使用者
		
	}
}
