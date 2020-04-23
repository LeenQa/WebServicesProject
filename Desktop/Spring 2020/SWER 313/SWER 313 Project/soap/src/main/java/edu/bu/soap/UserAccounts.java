package edu.bu.soap;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;


//mark class as an Entity   
@Entity
//defining class name as Table name  
@Table
@DynamicUpdate
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
	@Lob
	private byte[] userPhoto;
	@Column
	@CreationTimestamp
	private LocalDateTime creationDateTime;
	@Column
	private String email;
	
	public UserAccounts () {}
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

	public byte[] getUserPhoto() {
		return userPhoto;
	}

	public UserAccounts(String userName, String userPassword, Date birthDate, byte[] userPhoto,
			 String email) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.birthDate = birthDate;
		this.userPhoto = userPhoto;
		this.email = email;
	}

	public void setUserPhoto(byte[] userPhoto) {
		this.userPhoto = userPhoto;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
