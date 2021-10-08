package tw.finalproject.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Servlet Filter implementation class OpenSessionViewFileter
 */
@WebFilter("/OpenSessionViewFileter")
public class OpenSessionViewFilter implements Filter {

	private Session session;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		try {
			//從記憶體拿到SessionFactory(不是新建的意思，一開始已經建過了)
			SessionFactory factory = HibernateUtil.getSessionFactory();
			this.session = factory.getCurrentSession();
			
			session.beginTransaction();
			System.out.println("Begin transaction !");
			
			chain.doFilter(request, response);
			
			//response結束後再做commit，commit後才關閉Session(解決lazy loading問題)
			session.getTransaction().commit();
			System.out.println("commit OK !");
			
		}catch(Exception e){
			session.getTransaction().rollback();
			System.out.println("sth error and rollback");
			e.printStackTrace();
		}finally {
			System.out.println("Session closed but factory still open(controlled by listener)");
		}
		
		
	}
}
