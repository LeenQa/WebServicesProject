package edu.bu.soap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//mark class as an Entity   
@Entity
//defining class name as Table name  
@Table
public class Countries {
	@Id
	@Column
	private int CountryCode;
	@Column
	private String CountryName;

	public int getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(int countryCode) {
		CountryCode = countryCode;
	}

	public String getCountryName() {
		return CountryName;
	}

	public void setCountryName(String countryName) {
		CountryName = countryName;
	}

}
