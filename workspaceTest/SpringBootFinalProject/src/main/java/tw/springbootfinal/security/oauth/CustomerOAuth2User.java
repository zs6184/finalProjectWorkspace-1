package tw.springbootfinal.security.oauth;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import tw.springbootfinal.users.model.AuthenticationProvider;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

public class CustomerOAuth2User implements OAuth2User {

	private OAuth2User oauth2User;
	
	//設定google帳號權限
	private static final List<GrantedAuthority> MEMBER_ROLES = AuthorityUtils.createAuthorityList("ROLE_MEMBER");

	@Autowired
	private CustomerService cusService;
	
	public CustomerOAuth2User(OAuth2User oauth2User) {
		this.oauth2User = oauth2User;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return oauth2User.getAttributes();
	}

	//回傳設定權限
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("MEMBER_ROLES: "+MEMBER_ROLES);
		return MEMBER_ROLES;//會員權限
	}

	//取得使用者名稱
	@Override
	public String getName() {
		return oauth2User.getAttribute("name"); //返回google帳戶名稱，例如:王小明
	}
	
    public String getEmail() {
    	System.out.println("email測試: "+oauth2User.getAttribute("email"));
        return oauth2User.getAttribute("email");   
    }
	
    //測試區
	public String getRealName() {
		CustomerBean cusBean = cusService.getByCusUsername(getName());
		
		//取得資料庫的provider資料:如google
		AuthenticationProvider authProvide = cusBean.getAuthProvide();
		System.out.println("authProvide: "+authProvide);
		
		String realName = (String)oauth2User.getAttribute("name");
		String email = (String)oauth2User.getAttribute("email");
		System.out.println("realName: "+realName);
		System.out.println("email: "+email);
		
		return oauth2User.getAttribute("name"); //返回google帳戶名稱
	}

}
