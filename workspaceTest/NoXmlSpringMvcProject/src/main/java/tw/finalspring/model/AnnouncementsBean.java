package tw.finalspring.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Announcements")
@Component("AnnouncementsBean")
public class AnnouncementsBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
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
	
	@Column(name="picture")
	private byte[] picture;


	


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


	public byte[] getPicture() {
		return picture;
	}


	public void setPicture(byte[] picture) {
		this.picture = picture;
	}


	public AnnouncementsBean(Integer announceID, Integer empID, String headline, String releaseTime, String articleCont,
			byte[] picture) {
		super();
		this.announceID = announceID;
		this.empID = empID;
		this.headline = headline;
		this.releaseTime = releaseTime;
		this.articleCont = articleCont;
		this.picture = picture;
	}


	public AnnouncementsBean() {
		
	}


	@Override
	public String toString() {
		return "AnnouncementsBean [announceID=" + announceID + ", empID=" + empID + ", headline=" + headline
				+ ", releaseTime=" + releaseTime + ", articleCont=" + articleCont +
				"]";
	}
	
	public AnnouncementsBean setBean(AnnouncementsBean temp) {
		
		this.setEmpID(temp.getEmpID());
		this.setHeadline(temp.getHeadline());
		this.setReleaseTime(temp.getReleaseTime());
		this.setArticleCont(temp.getArticleCont());
		this.setPicture(temp.getPicture());
	
		
		return this;
	}
	
	
}
