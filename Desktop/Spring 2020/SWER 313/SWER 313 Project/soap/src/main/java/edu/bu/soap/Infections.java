package edu.bu.soap;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//mark class as an Entity   
@Entity
//defining class name as Table name  
@Table
public class Infections {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	@JoinTable(name = "Countries", joinColumns = @JoinColumn(name = "countryCode"), inverseJoinColumns = @JoinColumn(name = "theCountryCode"))
	private int theCountryCode;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date dtReported;
	@JoinTable(name = "UsersAccounts", joinColumns = @JoinColumn(name = "reportedBy"), inverseJoinColumns = @JoinColumn(name = "userName"))
	private String reportedBy;
	@Column
	private String sourceURL;
	@Column
	private int numOfInfections;
	@Column
	private int numOfDeaths;
	@Column
	private int numOfRecoveries;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDtReported() {
		return dtReported;
	}

	public void setDtReported(Date dtReported) {
		this.dtReported = dtReported;
	}

	public int getCountryCode() {
		return theCountryCode;
	}

	public void setCountryCode(int countryCode) {
		this.theCountryCode = countryCode;
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
		return numOfInfections;
	}

	public void setNumOfInections(int numOfInections) {
		this.numOfInfections = numOfInections;
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
