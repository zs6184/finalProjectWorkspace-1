package tw.springbootfinal.mail;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
public class mailConfig {
	
	@Bean("javaMailSender")
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("ik2469181@gmail.com");
		mailSender.setPassword("jvvwmqblclsldqzf");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		return mailSender;
	}

	@Bean
	public FreeMarkerConfigurer freemarkerConfig() {
		final FreeMarkerConfigurer result = new FreeMarkerConfigurer();
		result.setTemplateLoaderPath("/WEB-INF/templates/");
		result.setDefaultEncoding("UTF-8");
		return result;
	}

}
