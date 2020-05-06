package edu.bu.soap.countries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
  
/**A class that contains the columns of the countries entity and the methods used to get and set these columns*/
@Entity
@Table
public class Countries {
	@Id
	@Column
	private int countryCode;
	@Column
	private String countryName;

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
