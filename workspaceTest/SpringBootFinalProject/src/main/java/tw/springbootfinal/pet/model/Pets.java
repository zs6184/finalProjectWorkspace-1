package tw.springbootfinal.pet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "pets")
@Component
public class Pets {
	
	@Id @Column(name = "pet_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer petId;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "species")
	private String species;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "pet_name")
	private String petName;
	
	@Column(name = "age")
	private String age;
	
	@Column(name = "fix_status")
	private String fixStatus;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "adopt_status")
	private String adoptStatus;
	
	@Column(name = "adopt_date")
	private String adoptDate;
	
	@Column(name = "cus_ID")
	private Integer cusId;
	
	@Column(name = "cus_realname")
	private String cusName;
	
	@Column(name = "pic")
	private byte[] pic;

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFixStatus() {
		return fixStatus;
	}

	public void setFixStatus(String fixStatus) {
		this.fixStatus = fixStatus;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAdoptStatus() {
		return adoptStatus;
	}

	public void setAdoptStatus(String adoptStatus) {
		this.adoptStatus = adoptStatus;
	}

	public String getAdoptDate() {
		return adoptDate;
	}

	public void setAdoptDate(String adoptDate) {
		this.adoptDate = adoptDate;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
	public Pets setBean(Pets temp) {
		this.setPetId(temp.getPetId());
		this.setCategory(temp.getCategory());
		this.setSpecies(temp.getSpecies());
		this.setSex(temp.getSex());
		this.setPetName(temp.getPetName());
		this.setAge(temp.getAge());
		this.setFixStatus(temp.getFixStatus());
		this.setNote(temp.getNote());
		this.setAdoptStatus(temp.getAdoptStatus());
		this.setAdoptDate(temp.getAdoptDate());
		this.setCusId(temp.getCusId());
		this.setCusName(temp.getCusName());
		return this;
	}
}
