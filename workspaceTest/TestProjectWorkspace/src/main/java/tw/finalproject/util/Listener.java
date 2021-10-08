package tw.finalproject.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class Listener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
        HibernateUtil.getSessionFactory();
        System.out.println("SessionFactory created !");
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
         HibernateUtil.closeSessionFactory();
         System.out.println("SessionFactory closed !");
    }

	
}
