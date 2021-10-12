//package tw.finalspring.service;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//
//import freemarker.core.ParseException;
//import freemarker.template.Configuration;
//import freemarker.template.MalformedTemplateNameException;
//import freemarker.template.TemplateException;
//import freemarker.template.TemplateNotFoundException;
//
//@Component
//public class MailService {
//
//	@Autowired
//	@Qualifier("javaMailSender")
//	JavaMailSender mailSender;
//	
//	@Autowired
//	Configuration config;
//
//	public boolean sendMail() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		try {
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
//			helper.setFrom("ik2469181@gmail.com");
//			helper.setTo("q2469181q@gmail.com");
//			helper.setSubject("�D���GHello J.J.Huang �O�J�R�A�귽");
//			// helper.setText("<html><body><img src=\"cid:hotmail\"
//			// ><div>hihi</div><h1>�ڬO����h1</h1></body></html>", true);
//			Map<String, Object> model = new HashMap<String, Object>();
//			model.put("userName", "���թm�W");
//			String templateString = FreeMarkerTemplateUtils
//					.processTemplateIntoString(config.getTemplate("MailMarker.html"), model);
//			helper.setText(templateString, true);
//			FileSystemResource file = new FileSystemResource(new File("D:\\aa123.jpg"));
//
//			helper.addInline("imgfile", file);
//			mailSender.send(mimeMessage);
//			return true;
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			return false;
//
//		}
//
//	}
//}
