package edu.bu.soap.countries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**a class that combines countries and infections to use it when the user requests to get infections in a specific country or all countries*/
public class CountriesInfections {

	int countryCode;
	String countryName;
	int numOfInfections;
	int numOfDeaths;
	int numOfRecoveries;

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

	public int getNumOfInfections() {
		return numOfInfections;
	}

	public void setNumOfInfections(int numOfInfections) {
		this.numOfInfections = numOfInfections;
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

	public CountriesInfections(int countryCode, String countryName, int numOfInfections, int numOfDeaths,
			int numOfRecoveries) {
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.numOfInfections = numOfInfections;
		this.numOfDeaths = numOfDeaths;
		this.numOfRecoveries = numOfRecoveries;
	}

	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
}
