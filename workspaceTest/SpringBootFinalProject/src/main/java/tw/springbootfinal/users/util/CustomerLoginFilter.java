//package tw.springbootfinal.users.util;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//public class CustomerLoginFilter extends UsernamePasswordAuthenticationFilter {
//	
//	//自定義過濾器
//	public CustomerLoginFilter(String loginUrl, String httpMethod) {
//		setUsernameParameter("username");
//		
//		//攔截過濾器或是這個http請求的登入
//		super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(loginUrl,httpMethod));
//	}
//
//	@Override //這裡面的方法將在spring驗證前先被調用來處理身分驗證
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException {
//		//在這裡編寫關於recaptcha的
//		System.out.println("Before processing authentication....");
//		
//		return super.attemptAuthentication(request, response);
//	}
//	
//}
