package iii.homework.model;

import java.io.Serializable;
import java.util.Arrays;

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

	public CustomerBean() {
	}

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

}