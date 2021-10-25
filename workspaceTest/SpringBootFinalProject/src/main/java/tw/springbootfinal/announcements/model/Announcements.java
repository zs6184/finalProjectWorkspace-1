package tw.springbootfinal.announcements.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Announcements")
@Component
public class Announcements {
	
	
	@Id
	@Column(name = "announce_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer announceID;
	
	@Column(name="emp_ID")
	private Integer empID;
	
	@Column(name="headline")
	private String headline;
	
	@Column(name="release_time")
	private String releaseTime;
	
	@Column(name="article_cont")
	private String articleCont;
	
	@Column(name="pic")
	private byte[] pic;


	


	public Integer getAnnounceID() {
		return announceID;
	}


	public void setAnnounceID(Integer announceID) {
		this.announceID = announceID;
	}


	public Integer getEmpID() {
		return empID;
	}


	public void setEmpID(Integer empID) {
		this.empID = empID;
	}


	public String getHeadline() {
		return headline;
	}


	public void setHeadline(String headline) {
		this.headline = headline;
	}


	public String getReleaseTime() {
		return releaseTime;
	}


	public void setReleaseTime(String releaseTime) {
		
		this.releaseTime = releaseTime;
	}


	public String getArticleCont() {
		return articleCont;
	}


	public void setArticleCont(String articleCont) {
		this.articleCont = articleCont;
	}


	public byte[] getPic() {
		return pic;
	}


	public void setPic(byte[] pic) {
		this.pic = pic;
	}


	


	public Announcements() {
		
	}



	public Announcements setBean(Announcements temp) {
		
		this.setEmpID(temp.getEmpID());
		this.setHeadline(temp.getHeadline());
		this.setReleaseTime(temp.getReleaseTime());
		this.setArticleCont(temp.getArticleCont());
		this.setPic(temp.getPic());
	
		
		return this;
	}
	
	
}
