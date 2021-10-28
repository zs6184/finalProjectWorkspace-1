package tw.springbootfinal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/stylesheet/**").addResourceLocations("/WEB-INF/resources/stylesheet/");
		registry.addResourceHandler("/javascript/**").addResourceLocations("/WEB-INF/resources/javascript/");
		registry.addResourceHandler("/font/**").addResourceLocations("/WEB-INF/resources/font/");
		registry.addResourceHandler("/image/**").addResourceLocations("/WEB-INF/resources/image/","/WEB-INF/resources/image/downloadTempDir/","/WEB-INF/resources/image/uploadTempDir/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login.Controller").setViewName("login");//登入頁面
		registry.addViewController("/loginerror.Controller").setViewName("loginerror");//登入失敗頁面
		registry.addViewController("/Users/logout.Controller").setViewName("logout");//登出頁面
		registry.addViewController("/Users/ChangePassword.Controller").setViewName("changePassword");//更換密碼頁面
		registry.addViewController("/backstageLogin.Controller").setViewName("backstageLogin");//後台登入頁面
		registry.addViewController("/backstageLoginerror.Controller").setViewName("backstageLoginerror");//後台登入失敗頁面
		
		
	}

	
	
}
