package tw.finalproject.util;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	
	private static final SessionFactory factory=createSessionFactory();
	
	private static SessionFactory createSessionFactory() {
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();//若configure中檔名不同才需要寫，本行這個狀況可省略的
		SessionFactory factory=new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		return factory;
	}
	
	public static SessionFactory getSessionFactory() { //一個public的取得sessionfactory的靜態方法(只會產生一個-使用單例模式)
		return factory;
	}
	
	public static void closeSessionFactory() {
		if(factory!=null) { //當有factory沒被關閉才執行關閉
			factory.close();
		}
	}
}
