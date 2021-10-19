package tw.finalspring.model;

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
	@Column(name = "cus_ID")
	private int cusId;

	@Column(name = "cus_username")
	private String cusUsername;

	@Column(name = "cus_pwd")
	private String cusPassword;

	@Column(name = "aka")
	private String aka;

	@Column(name = "cus_realname")
	private String cusRealname;

	@Column(name = "gender")
	private String gender;

	@Column(name = "phone")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "birth")
	private String birthdate;

	@Column(name = "address")
	private String address;

	@Column(name = "photo")
	private byte[] image;

	@Column(name = "photoName")
	private String imageName;

	@Column(name = "note")
	private String note;

	@Column(name = "missing_count")
	private int noShow;

	public CustomerBean() {
	}

	@Override
	public String toString() {
		return "CustomerBean [cusId=" + cusId + ", cusUsername=" + cusUsername + ", cusPassword=" + cusPassword
				+ ", aka=" + aka + ", cusRealname=" + cusRealname + ", gender=" + gender + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", birthdate=" + birthdate + ", address=" + address + ", image="
				+ Arrays.toString(image) + ", note=" + note + ", noShow=" + noShow + "]";
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

	public String getAka() {
		return aka;
	}

	public void setAka(String aka) {
		this.aka = aka;
	}

	public String getCusRealname() {
		return cusRealname;
	}

	public void setCusRealname(String cusRealname) {
		this.cusRealname = cusRealname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getNoShow() {
		return noShow;
	}

	public void setNoShow(int noShow) {
		this.noShow = noShow;
	}

}
