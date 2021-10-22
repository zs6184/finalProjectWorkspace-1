package tw.springbootfinal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController	//@Controller+@Repository
@RequestMapping("/testenv")
public class HelloWorldTest {
	@RequestMapping("/hello.controller")
	public String processAction() {
		return "Hello,world";
	}
}
