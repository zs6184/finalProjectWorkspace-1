package tw.finalspring.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

//此Class繼承AbstractAnnotationConfigDispatcherServletInitializer類別
//相當於Web.xml
	
	
//用來設定相當於beans.config.xml的java程式組態類別	
	
	@Override	
	protected Class<?>[] getRootConfigClasses() {		
		return new Class[] {RootAppConfig.class};
		//return null;
	}

	
//用來設定相當於mvc-servlet.xml的java程式組態類別	
	@Override	
	protected Class<?>[] getServletConfigClasses() {		
		return new Class[] {WebAppMvcConfig.class};
		//return null;
	}

	
//用來設定url-pattern		
	@Override	
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	
//註冊Filter	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodeFilter = new CharacterEncodingFilter();
		encodeFilter.setEncoding("UTF-8");
		encodeFilter.setForceEncoding(true);
		
		//上三行等價寫法 
		//CharacterEncodingFilter encodeFilter = new CharacterEncodingFilter("UTF-8", true);
		
		return new Filter[] {encodeFilter};
	}

}
