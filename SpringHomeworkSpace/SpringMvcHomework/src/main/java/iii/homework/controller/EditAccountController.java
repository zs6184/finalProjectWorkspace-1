package iii.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import iii.homework.service.CustomerService;

@Controller
public class EditAccountController {

	@Autowired
	private CustomerService cusService;
	

}
