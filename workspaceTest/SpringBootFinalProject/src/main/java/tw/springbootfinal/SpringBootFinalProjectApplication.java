package tw.springbootfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootFinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFinalProjectApplication.class, args);
	}
	
}
