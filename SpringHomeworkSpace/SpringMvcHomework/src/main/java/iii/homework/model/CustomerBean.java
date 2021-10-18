package iii.homework.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Customers")
@Component("customerBean")
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int cusId;

	@Column(name = "username")
	private String cusUsername;

	@Column(name = "password")
	private String cusPassword;

	@Column(name = "realName")
	private String cusRealname;

<<<<<<< HEAD
=======
	public CustomerBean() {
	}
	
	public CustomerBean(Integer cusId,String cusUsername,
				String cusPassword,String cusRealname) {
		super();
		this.setCusId(cusId);
		this.setCusUsername(cusUsername);
		this.setCusPassword(cusPassword);
		this.setCusRealname(cusRealname);
	}

>>>>>>> 0a89dbdc21b6af8f74c9917f8e0680305e639135
	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getCusUsername() {
		return cusUsername;
	}

	public void setCusUsername(String cusUsername) {
		this.cusUsername = cusUsername;
	}

	public String getCusPassword() {
		return cusPassword;
	}

	public void setCusPassword(String cusPassword) {
		this.cusPassword = cusPassword;
	}

	public String getCusRealname() {
		return cusRealname;
	}

	public void setCusRealname(String cusRealname) {
		this.cusRealname = cusRealname;
	}

	public CustomerBean setBean(CustomerBean temp) {
		this.setCusId(temp.getCusId());
		this.setCusUsername(temp.getCusUsername());
		this.setCusRealname(temp.getCusRealname());
		this.setCusPassword(temp.getCusPassword());
		
		return this;
	}
}
