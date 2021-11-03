package tw.springbootfinal.security.oauth;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import tw.springbootfinal.users.model.AuthenticationProvider;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private CustomerService cusService;
	
	//驗證成功時
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		//取得google使用者資訊
		CustomerOAuth2User oAuthUser = (CustomerOAuth2User) authentication.getPrincipal();
		String email = oAuthUser.getEmail();
		String realName = oAuthUser.getName();
		
		System.out.println("Customer's email: "+email);
		System.out.println("email="+email+"realName="+realName);
		
		List<CustomerBean> cusList = cusService.findByEmailForGoogleOAuth(email);
		HttpSession session = request.getSession();
		//設成session讓網站使用
		session.setAttribute("realName", realName);
		session.setAttribute("username", email); //google帳戶登入者，email當成他的帳號
		
		
		//如果這個google帳號未登入過，就將資料寫進資料庫
		if(cusList.isEmpty()) {
			cusService.createNewCustomerAfterOAuthLoginSuccess(realName, email, AuthenticationProvider.GOOGLE);
		}else {//登入過就進行資料更新
			CustomerBean cusBean=cusList.get(0);
			cusService.updateCustomerAfterOAuthLoginSuccess(cusBean, realName, AuthenticationProvider.GOOGLE);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
