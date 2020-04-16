package edu.bu.soap;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

//mark class as an Entity   
@Entity
//defining class name as Table name  
@Table
public class UserAccounts {
	
	@Id
	@Column
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String userName;
	@Column
	private String userPassword;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	@Column
	private String userPhoto;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date creationDateTime;
	@Column
	private String email;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
