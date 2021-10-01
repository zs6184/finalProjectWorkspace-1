package test.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import test.company.model.CompanyBean;

public class TestCompanyBean {

	public static void main(String[] args) {
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		
		Session session =factory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		CompanyBean comBean = new CompanyBean(1006,"BongFrog");
		
		session.save(comBean);
		
		tx.commit();
		
		session.close();
		factory.close();
	}

}
