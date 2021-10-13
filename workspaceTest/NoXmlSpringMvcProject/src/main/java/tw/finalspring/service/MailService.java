package tw.finalspring.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import tw.finalspring.model.CustomerBean;

@Component
public class MailService {

	@Autowired
	@Qualifier("javaMailSender")
	JavaMailSender mailSender;

	@Autowired
	FreeMarkerConfigurer freemarkerConfig;
	
	
	public boolean sendMail(HttpServletRequest request,CustomerBean user) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException,
			IOException, TemplateException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
<<<<<<< HEAD
		//®˙±oLogo∏ÙÆ|
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/templates/image/logo.png");
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			//≥]∏m¨€√ˆ´HÆß
			helper.setFrom("ik2469181@gmail.com");
			helper.setTo(user.getEmail());
			helper.setSubject("•D¶Æ°GÆˆ∏Ò≠´≥]±KΩX");
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("userName", user.getCusUsername());
			//∏¸§Jº“™O
=======
		//ÂèñÂæólegoË∑ØÂæë
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/templates/image/logo.png");
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			//ÈÖçÁΩÆ‰ø°ÊÅØ
			helper.setFrom("ik2469181@gmail.com");
			//helper.setTo(user.getEmail());
			helper.setTo("Êî∂‰ª∂‰∫∫");
			helper.setSubject("Ëº∏ÂÖ•Ê®ôÈ°å");
			Map<String, Object> model = new HashMap<String, Object>();
			//model.put("userName", user.getCusUsername());
			model.put("userName", "Ê∏¨Ë©¶");
			//ËºâÂÖ•Ê®°Êùø
>>>>>>> master
			String templateString = FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfig.getConfiguration().getTemplate("mailMarker.html"), model);
			helper.setText(templateString, true);
			FileSystemResource file = new FileSystemResource(new File(path));			
<<<<<<< HEAD
			//•[§Jlego
			helper.addInline("imgfile", file);
			//∞e•X¶^∂«true
=======
			//ÂúñÁâá
			helper.addInline("imgfile", file);
			//ÈÄÅÂá∫ÂõûÂÇ≥true
>>>>>>> master
			mailSender.send(mimeMessage);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}

	}
}
