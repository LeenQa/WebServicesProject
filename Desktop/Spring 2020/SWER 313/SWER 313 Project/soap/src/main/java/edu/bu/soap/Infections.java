package edu.bu.soap;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

//mark class as an Entity   
@Entity
//defining class name as Table name  
@Table
public class Infections {
	@JoinTable( name = "Countries", joinColumns = @JoinColumn ( name = "countryCode"), inverseJoinColumns = @JoinColumn( name = "countryCode"))
	private int countryCode;
	@Column
	private Date dtReported;
	@JoinTable( name = "UsersAccounts", joinColumns = @JoinColumn ( name = "reportedBy"), inverseJoinColumns = @JoinColumn( name = "userName"))
	private String reportedBy;
	@Column
	private String sourceURL;
	@Column
	private int numOfInections;
	@Column
	private int numOfDeaths;
	@Column
	private int numOfRecoveries;

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public Date getDateReported() {
		return dtReported;
	}

	public void setDateReported(Date dateReported) {
		this.dtReported = dateReported;
	}

	public String getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	public String getSourceURL() {
		return sourceURL;
	}

	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}

	public int getNumOfInections() {
		return numOfInections;
	}

	public void setNumOfInections(int numOfInections) {
		this.numOfInections = numOfInections;
	}

	public int getNumOfDeaths() {
		return numOfDeaths;
	}

	public void setNumOfDeaths(int numOfDeaths) {
		this.numOfDeaths = numOfDeaths;
	}

	public int getNumOfRecoveries() {
		return numOfRecoveries;
	}

	public void setNumOfRecoveries(int numOfRecoveries) {
		this.numOfRecoveries = numOfRecoveries;
	}

}
