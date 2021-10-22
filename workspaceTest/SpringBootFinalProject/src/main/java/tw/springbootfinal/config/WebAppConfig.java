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
		registry.addResourceHandler("/image/**").addResourceLocations("/WEB-INF/resources/image/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login.Controller").setViewName("login");
		
	}

	
	
}
