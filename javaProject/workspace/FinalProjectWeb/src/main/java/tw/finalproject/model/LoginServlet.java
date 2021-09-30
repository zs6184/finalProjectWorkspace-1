package tw.finalproject.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.finalproject.util.HibernateUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//取得帳號密碼
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		CustomerBean cusBean = new CustomerBean();
		cusBean.setCusUsername(username);
		
		//取得SessionFactoryListener建立好的SessionFactory
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		//取得service物件帶入session
		CustomerService cusServicse = new CustomerService(session);
//		
		List<CustomerBean> list = cusServicse.selectUsername(cusBean);
		
		System.out.println(password);
		if(list.isEmpty()) {
			System.out.println("帳號錯誤");
			
		}else {
			for(CustomerBean oneCus:list) {
				String pwd = oneCus.getCusPassword();
				if(pwd.equals(password)) {
					System.out.printf("歡迎 %s 成功登入\n",oneCus.getCusRealname());
				}else {
					System.out.println("密碼錯誤");
				}
			}
		}

		
		
	}

}
