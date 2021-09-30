package tw.finalproject.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.finalproject.util.HibernateUtil;


@WebServlet("/CreateCusAccount")
public class CreateCusAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//取得表單資料
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String nickName = request.getParameter("nickName");
		String gender = request.getParameter("gender");
		String birthdate = request.getParameter("birthdate");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
			CustomerBean cBean = new CustomerBean();
			
			//設定到Bean
			cBean.setCusUsername(username);
			cBean.setCusPassword(password);
			cBean.setCusRealname(name);
			cBean.setAka(nickName);
			cBean.setGender(gender);
			cBean.setBirthdate(birthdate);
			cBean.setPhoneNumber(phoneNumber);
			cBean.setEmail(email);
			cBean.setAddress(address);
			
			CustomerService cusService = new CustomerService(session);
			//新增資料
			cusService.insert(cBean);
			
			
			//轉到登入頁面
			request.getRequestDispatcher("login.html").forward(request, response);

	}

}
