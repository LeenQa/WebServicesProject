package edu.bu.soap.useraccounts;

import java.util.Date;
import java.util.TimeZone;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


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
	private Date birthDate;
	@Lob
	private byte[] userPhoto;
	@Column
	@CreationTimestamp
	private LocalDateTime creationDateTime;
	@Column
	private String email;
	
	private String photoID;
	
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

	public void setBirthDate(Date date) {
		
		this.birthDate = date;
	}

	public byte[] getUserPhoto() {
		return userPhoto;
	}

	public UserAccounts(String userName, String userPassword, Date birthDate,String photoID, byte[] userPhoto,
			 String email) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.birthDate = birthDate;
		this.userPhoto = userPhoto;
		this.email = email;
		this.photoID = photoID;
	}
	
	public UserAccounts(String userName, String userPassword, Date birthDate,
			 String email,String photoID, LocalDateTime creationDateTime) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.birthDate = birthDate;
		this.email = email;
		this.creationDateTime = creationDateTime;
		this.photoID = photoID;
	}

	public String getPhotoID() {
		return photoID;
	}
	public void setPhotoID(String photoID) {
		this.photoID = photoID;
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

	public String toString() {
		
		/*return "{\r\n" + 
				"        \"userName\": \""+ this.userName + "\",\r" + 
				"        \"userPassword\": \""+this.userPassword+"\",\r" + 
				"        \"birthDate\": \""+this.birthDate+"\",\r" + 
				"        \"creationDateTime\": \""+this.getCreationDateTime()+"\",\r" + 
				"        \"email\": \""+this.email+"\",\r" + 
				"        \"photoID\": \""+this.photoID+"\"\r\"" + 
				"    }\r";*/
		return "{ \"userName\" : \"" + this.userName + "\" , \"userPassword\" : \""+this.userPassword+ "\" ,\"birthDate\" : \""+this.birthDate+"\" ,\"creationDateTime\" : \""
				+this.getCreationDateTime()+ "\" ,\"email\" : \""+this.email+"\" ,\"photoID\" : \"" +this.photoID+ "\"}";
		
	}
}
