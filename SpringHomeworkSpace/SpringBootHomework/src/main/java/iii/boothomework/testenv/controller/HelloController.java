package iii.boothomework.testenv.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController	//@Controller+@Repository
@RequestMapping("/testenv")
public class HelloController {
	@RequestMapping("/hello.controller")
	public String processAction() {
		return "Hello,world";
	}
}
