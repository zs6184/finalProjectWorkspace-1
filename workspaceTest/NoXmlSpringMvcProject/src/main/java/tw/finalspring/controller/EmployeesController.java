package tw.finalspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeesController {

	@RequestMapping("/Employees.Controller")
	public String processEmployees() {
		return "employees";
	}
}
