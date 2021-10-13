package tw.finalspring.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employees")
public class EmployeeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_ID")
	private int empId;

	@Column(name = "emp_username")
	private String empUsername;

	@Column(name = "emp_pwd")
	private String empPassword;

	@Column(name = "emp_realname")
	private String empRealname;

	@Column(name = "title")
	private String title;

	@Column(name = "gender")
	private String gender;

	@Column(name = "phone")
	private String phoneNumber;

	@Column(name = "birth")
	private String birthdate;

	@Column(name = "hire_date")
	private String hiredate;

	@Column(name = "address")
	private String address;

	@Column(name = "photo")
	private byte[] image;

	@Column(name = "note")
	private String note;

	@Override
	public String toString() {
		return "EmployeeBean [empId=" + empId + ", empUsername=" + empUsername + ", empPassword=" + empPassword
				+ ", empRealname=" + empRealname + ", title=" + title + ", gender=" + gender + ", phoneNumber="
				+ phoneNumber + ", birthdate=" + birthdate + ", hiredate=" + hiredate + ", address=" + address
				+ ", image=" + Arrays.toString(image) + ", note=" + note + "]";
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpUsername() {
		return empUsername;
	}

	public void setEmpUsername(String empUsername) {
		this.empUsername = empUsername;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmpRealname() {
		return empRealname;
	}

	public void setEmpRealname(String empRealname) {
		this.empRealname = empRealname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
