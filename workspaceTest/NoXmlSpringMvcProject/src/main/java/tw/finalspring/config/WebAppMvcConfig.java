package tw.finalspring.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//相當於mvc-servlet.xml
//協助啟用組態設定程式，可幫忙把bean註冊進來
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
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(6);
		
		return viewResolver;
	}

	@Override //可在對應位置建好資料夾，未來透過此虛擬路徑(images)找到存放的圖片,就不用打太長的檔案位置路徑
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
	}

	@Override//根據虛擬路徑重新導向至對應路徑
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/WEB-INF/pages", "membersmainpage.controller");
	}
	
	@Bean
	//需要這個才能使用commons-fileupload上傳文件
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}

}
