package tw.finalspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//���mvc-servlet.xml
//����蝯�身摰���撟怠��ean閮餃��脖��
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "tw.finalspring")
public class WebAppMvcConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		// viewResolver.setSuffix(".html");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(6);

		return viewResolver;
	}

	@Override // ��撠��蔭撱箏末鞈�冗嚗靘��迨��頝臬��(images)��摮�����,撠曹���云������蔭頝臬��
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
		registry.addResourceHandler("/stylesheet/**").addResourceLocations("/WEB-INF/pages/stylesheet/");
		registry.addResourceHandler("/javascript/**").addResourceLocations("/WEB-INF/pages/javascript/");
		registry.addResourceHandler("/image/**").addResourceLocations("/WEB-INF/pages/image/");
		registry.addResourceHandler("/font/**").addResourceLocations("/WEB-INF/pages/font/");

	}

	@Override // ����頝臬��撠�撠�楝敺�
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "membersmainpage.controller");
	}

}
