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

import tw.springbootfinal.users.model.AuthEmpUserDetailService;
import tw.springbootfinal.users.model.AuthUserDetailService;

@EnableWebSecurity
public class SecurityConfig {
	
	@Configuration
	@Order(1)//用來設定順序
	public static class EmployeeConfigurationAdapter extends WebSecurityConfigurerAdapter{

		@Autowired //實作UserDetailsService進行員工帳密驗證
		private AuthEmpUserDetailService authEmpUserDetailService;

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
			.antMatcher("/Backstage/**")
			.authorizeRequests()

			.antMatchers("/Backstage/**").hasAnyRole("EMPLOYEE","ADMIN")
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers(HttpMethod.POST).permitAll()
			.anyRequest().authenticated()
			
			.and()
			.rememberMe().tokenValiditySeconds(3600).key("rememberMe-key")
			
			.and()
			.csrf().disable()
			.formLogin()
			.loginPage("/backstageLogin.Controller")
			.defaultSuccessUrl("/Backstage/SelectCustomerAll.Controller")
			.failureUrl("/backstageLoginerror.Controller")
			
			.and()
			.logout().logoutUrl("/Users/logout.Controller")
			.logoutSuccessUrl("/logout.Controller");
		}
	}
	
	
	@Configuration
	@Order(2) //用來設定順序
	public static class CustomerConfigurationAdapter extends WebSecurityConfigurerAdapter{

		@Autowired //實作UserDetailsService進行會員帳密驗證
		private AuthUserDetailService authUserDetailService;
		
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

            .antMatchers("/Users/**").hasRole("MEMBER")
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers(HttpMethod.POST).permitAll()
			.anyRequest().authenticated()
			
			.and()
			.rememberMe().tokenValiditySeconds(3600).key("rememberMe-key")
			
			.and()
			.csrf().disable()
			.formLogin()
			.loginPage("/login.Controller")
			.defaultSuccessUrl("/Users/loginIndex.Controller")
			.failureUrl("/loginerror.Controller")
			
			.and()
			.logout().logoutUrl("/Users/logout.Controller")
			.logoutSuccessUrl("/logout.Controller");
		}
	}
}
