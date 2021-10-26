package tw.springbootfinal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tw.springbootfinal.users.model.AuthUserDetailService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthUserDetailService authUserDetailService;
	
//	@Autowired
//	private AuthEmpUserDetailService authEmpUserDetailService;
	
	//設定角色權限
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("authUserDetailService-1"+authUserDetailService);
//		System.out.println("authEmpUserDetailService-1"+authEmpUserDetailService);
//		DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
//		ProviderManager providerManager = new ProviderManager();
		auth
		.userDetailsService(authUserDetailService)
		.passwordEncoder(new BCryptPasswordEncoder());
//		.and()
//		.userDetailsService(authEmpUserDetailService)
//		.passwordEncoder(new BCryptPasswordEncoder());
//		.parentAuthenticationManager(providerManager);
		System.out.println("authUserDetailService"+authUserDetailService);
//		System.out.println("authEmpUserDetailService"+authEmpUserDetailService);
	}

	//web安全性控制
	@Override
	public void configure(WebSecurity web) throws Exception {
	}

	//攔截請求進行驗證
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
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
		.csrf().disable()
		.formLogin().loginPage("/login.Controller")
		.defaultSuccessUrl("/Users/loginIndex.Controller")
		.failureUrl("/loginerror.Controller")
		.and()
		.logout().logoutUrl("/Users/logout.Controller")
		.logoutSuccessUrl("/index.html");
		
	}

	//針對多個資料表進行比對(員工、會員)
//	@Override
//	protected AuthenticationManager authenticationManager() throws Exception {
//		System.out.println("authenticationManager: dap");
//		DaoAuthenticationProvider dap1 = new DaoAuthenticationProvider();
//		dap1.setUserDetailsService(authUserDetailService);
//		dap1.setPasswordEncoder(new BCryptPasswordEncoder());
//		
//		DaoAuthenticationProvider dap2 = new DaoAuthenticationProvider();
//		dap2.setUserDetailsService(authEmpUserDetailService);
//		dap2.setPasswordEncoder(new BCryptPasswordEncoder());
//		
//		ProviderManager providerManager = new ProviderManager(dap1);
//		
//		return providerManager;
//	}

//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//	    
//	    endpoints
//	    
//
//	    .userDetailsService(userDetailsService);
//	}
	
}
