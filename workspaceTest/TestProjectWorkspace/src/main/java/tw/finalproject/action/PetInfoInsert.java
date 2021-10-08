package tw.finalproject.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.finalproject.model.PetDAO;
import tw.finalproject.util.HibernateUtil;

@WebServlet("/Servlet/PetInfoInsert")
public class PetInfoInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SessionFactory factory = HibernateUtil.getSessionFactory();
	Session session = factory.getCurrentSession();

	private PetDAO petDAO;
	
	public void init() {
		this.petDAO = new PetDAO(session);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
