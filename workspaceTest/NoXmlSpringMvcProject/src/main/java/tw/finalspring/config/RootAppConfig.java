package tw.finalspring.config;
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//相當於beans.config.xml
@Configuration
@ComponentScan(basePackages = "tw.finalspring")
@EnableTransactionManagement
@EnableWebMvc
public class RootAppConfig {
	
	@Bean
    public DataSource datasource() throws IllegalArgumentException, NamingException {
    	JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
    	jndiBean.setJndiName("java:comp/env/connectSqlServerJdbc/OrderService");
    	jndiBean.afterPropertiesSet();
    	DataSource ds = (DataSource)jndiBean.getObject();
    	return ds;
    }
	
	@Bean(destroyMethod = "destroy")
	public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(datasource());
		factory.setPackagesToScan("tw.finalspring");
		factory.setHibernateProperties(addProperties());
		return factory;
	}

	private Properties addProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
		props.put("hibernate.show_sql", Boolean.TRUE);
		props.put("hibernate.format_sql", Boolean.TRUE);
		return props;
	}
	
	@Bean(name="transactionManager")
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager mgr = new HibernateTransactionManager();
		mgr.setSessionFactory(sessionFactory);
		return mgr;
	}
}
