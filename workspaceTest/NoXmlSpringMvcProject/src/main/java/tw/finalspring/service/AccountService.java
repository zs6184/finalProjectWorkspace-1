package tw.finalspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.finalspring.model.Account;
import tw.finalspring.model.AccountDAO;

@Service @Transactional
public class AccountService {
	
	@Autowired
	private AccountDAO aDAO;
	
	public boolean checkLogin(Account users) {
		return aDAO.checkLogin(users);
	}
}
