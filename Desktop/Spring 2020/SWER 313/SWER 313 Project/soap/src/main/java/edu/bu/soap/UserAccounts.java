package edu.bu.soap;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//mark class as an Entity   
@Entity
//defining class name as Table name  
@Table
public class UserAccounts {
	
	@Id
	@Column
	private String UserName;
	@Column
	private String UserPassword;
	@Column
	private Date BirthDate;
	@Column
	private String UserPhoto;
	@Column
	private Date CreationDateTime;
	@Column
	private String Email;
	
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public Date getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(Date birthDate) {
		BirthDate = birthDate;
	}

	public String getUserPhoto() {
		return UserPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		UserPhoto = userPhoto;
	}

	public Date getCreationDateTime() {
		return CreationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		CreationDateTime = creationDateTime;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
}
