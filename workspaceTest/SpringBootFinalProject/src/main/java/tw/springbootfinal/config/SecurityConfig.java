package tw.springbootfinal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tw.springbootfinal.security.oauth.CustomerOAuth2User;
import tw.springbootfinal.security.oauth.CustomerOAuth2UserService;
import tw.springbootfinal.security.oauth.OAuth2LoginSuccessHandler;
import tw.springbootfinal.users.model.AuthEmpUserDetailService;
import tw.springbootfinal.users.model.AuthUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

	
	@Configuration
	@Order(1) //用來設定順序
	public static class EmployeeConfigurationAdapter extends WebSecurityConfigurerAdapter{
		public EmployeeConfigurationAdapter() {
			super();
		}
		
		@Autowired
		private AuthEmpUserDetailService authEmpUserDetailService;

		@Autowired
		private CustomerOAuth2UserService oAuth2UserService;
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
			.userDetailsService(authEmpUserDetailService)
			.passwordEncoder(new BCryptPasswordEncoder());
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			System.out.println("employee httpSecurity");
			http
			.antMatcher("/BK/**")
			.authorizeRequests()
			.antMatchers("/oauth2/**").permitAll() //google登入的權限
			//.anyRequest()
			//.hasAnyRole("EMPLOYEE","ADMIN")
			
			
			.antMatchers("/Backstage/**").hasAnyRole("EMPLOYEE","ADMIN")
			//.antMatchers("/Users/**").hasRole("MEMBER")
			.antMatchers(HttpMethod.GET,"/Backstage/**").authenticated()
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers(HttpMethod.POST,"/Backstage/**").authenticated()
			.antMatchers(HttpMethod.POST).permitAll()
			.anyRequest().authenticated()
			
			.and()
			.exceptionHandling().accessDeniedPage("/Users/accessDenied")
			.and()
			.rememberMe().tokenValiditySeconds(3600).key("rememberMe-key")
			
			.and()
			.csrf().disable()
			.formLogin()
			.loginPage("/BK/backstageLogin.Controller")
			.defaultSuccessUrl("/Users/loginIndex.Controller")
			.failureUrl("/BK/backstageLoginerror.Controller")
			
			.and()
			.logout().logoutUrl("/Users/logout.Controller")
			.logoutSuccessUrl("/logout.Controller");
		}
	}
	
	
	@Configuration
	@Order(2) //用來設定順序
	public static class CustomerConfigurationAdapter extends WebSecurityConfigurerAdapter{
		public CustomerConfigurationAdapter() {
			super();
		}

		@Autowired
		private AuthUserDetailService authUserDetailService;
		
		@Autowired
		private CustomerOAuth2UserService oAuth2UserService;
		
		@Autowired
		private OAuth2LoginSuccessHandler oAuth2loginSuccessHandler;
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
			.userDetailsService(authUserDetailService)
			.passwordEncoder(new BCryptPasswordEncoder());
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			System.out.println("customer httpSecurity");
			http
			
            //.antMatcher("/Users/**")
            .authorizeRequests()
            .antMatchers("/oauth2/**").permitAll() //google登入的權限

//            .antMatcher("/Users/**")
//            .authorizeRequests()
//            .anyRequest()
//            .authenticated()
//			.antMatcher("/Users/**")
			//.authorizeRequests()
//			.anyRequest()
//			.hasRole("MEMBER")
			
			.antMatchers("/Backstage/**").hasAnyRole("EMPLOYEE","ADMIN")
			.antMatchers("/Users/**").hasRole("MEMBER")
			.antMatchers(HttpMethod.GET,"/Users/**").authenticated()
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers(HttpMethod.POST,"/Users/**").authenticated()
			.antMatchers(HttpMethod.POST).permitAll()
			.anyRequest().authenticated()
			
			.and()
			.rememberMe().tokenValiditySeconds(3600).key("rememberMe-key")
			
			.and()
			.exceptionHandling().accessDeniedPage("/Users/accessDenied")
			.and()
			.csrf().disable()
			.formLogin()
			.loginPage("/login.Controller")
			.defaultSuccessUrl("/Users/loginIndex.Controller")
			.failureUrl("/loginerror.Controller")
			
			.and()
			.oauth2Login() //oauth登入設定
			.loginPage("/login.Controller")
			.userInfoEndpoint().userService(oAuth2UserService) //取得google使用者資訊
			.and()
			.successHandler(oAuth2loginSuccessHandler) //驗證成功後的處理
			
			.and()
			.logout().logoutUrl("/Users/logout.Controller")
			.logoutSuccessUrl("/logout.Controller");
			
			
			
			
			
		}
	}
}
