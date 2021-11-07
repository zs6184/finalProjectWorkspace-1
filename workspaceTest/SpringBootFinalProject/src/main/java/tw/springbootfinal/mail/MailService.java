package tw.springbootfinal.mail;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import tw.springbootfinal.reservation.model.AdoptReservation;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Component
public class MailService {
	@Autowired
	@Qualifier("javaMailSender")
	JavaMailSender mailSender;

	@Autowired
	FreeMarkerConfigurer freemarkerConfig;
	@Autowired
	private CustomerService cService;

	public boolean sendMail(HttpServletRequest request, CustomerBean user, Map<String, Object> model,
			String templateNmae, String head) throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		// 取得lego路徑
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/templates/image/logo.png");
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			// 配置信息
			helper.setFrom("ik2469181@gmail.com");
			helper.setTo(user.getEmail());
			// helper.setTo("azure.test.1120@gmail.com");
			helper.setSubject(head);
			// 存入佔位符參數
//			Map<String, Object> model = new HashMap<String, Object>();
			// 載入模板
			String templateString = FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfig.getConfiguration().getTemplate(templateNmae), model);
			helper.setText(templateString, true);
			FileSystemResource file = new FileSystemResource(new File(path));
			// 圖片
			helper.addInline("imgfile", file);
			// 送出回傳true
			mailSender.send(mimeMessage);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean sendMail(HttpServletRequest request, String email, Map<String, Object> model, String templateNmae,
			String head) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
//取得lego路徑
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/templates/image/logo.png");
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			// 配置信息
			helper.setFrom("ik2469181@gmail.com");
			helper.setTo(email);
			// helper.setTo("azure.test.1120@gmail.com");
			helper.setSubject(head);
			// 存入佔位符參數
//	Map<String, Object> model = new HashMap<String, Object>();
			// 載入模板
			String templateString = FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfig.getConfiguration().getTemplate(templateNmae), model);
			helper.setText(templateString, true);
			FileSystemResource file = new FileSystemResource(new File(path));
			// 圖片
			helper.addInline("imgfile", file);
			// 送出回傳true
			mailSender.send(mimeMessage);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}

	}

//--------------------------------------------------------------------------------------	
	
	//寄送複數封信(寵物預約取消進行通知用)
	public boolean sendMailForList(HttpServletRequest request, List<AdoptReservation> theReserves,String templateNmae, String head) 
			throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		// 取得LOGO路徑
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/templates/image/logo.png");
		try {
			System.out.println("寄送環節，需要寄送:"+theReserves.size()+"封");
			for(AdoptReservation aReserve : theReserves){
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
				//建立此筆預約相關的資料承接MAP
				Map<String, Object> model = new HashMap<String, Object>();
				//取得此筆預約的客戶資料
				CustomerBean aUser = cService.findById(aReserve.getCusId());
				// 配置信息
				helper.setFrom("ik2469181@gmail.com");
				helper.setTo(aUser.getEmail());
				helper.setSubject(head);
				model.put("theDate",aReserve.getReserveTime());
				model.put("thePet",aReserve.getPetName());
				model.put("realName",aReserve.getCusRealname());
				// 載入模板
				String templateString = FreeMarkerTemplateUtils
						.processTemplateIntoString(freemarkerConfig.getConfiguration().getTemplate(templateNmae), model);
				helper.setText(templateString, true);
				FileSystemResource file = new FileSystemResource(new File(path));
				// 圖片
				helper.addInline("imgfile", file);
				// 送出回傳true
				mailSender.send(mimeMessage);
				System.out.println("寄出一封");
			}
			return true;
			
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
//---------------------------------------------------------------------------------------------

}
