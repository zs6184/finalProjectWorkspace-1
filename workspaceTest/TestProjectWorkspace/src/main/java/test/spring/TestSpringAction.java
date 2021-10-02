package test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringAction {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		
		TestSpring test = (TestSpring)context.getBean("logProvider");
		test.log("Spring running test, Hello World !");
		
		((ConfigurableApplicationContext)context).close();
	}

}
