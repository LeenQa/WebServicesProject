package edu.bu.soap.countries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//defining the business logic  
@Service
public class CountriesService {
	@Autowired
	CountriesRepository countriesRepository;

//getting all countries record by using the method findaAll() of CrudRepository  
	public List<Countries> getAllCountries() {
		List<Countries> countries = new ArrayList<Countries>();
		countriesRepository.findAll().forEach(countries1 -> countries.add(countries1));
		return countries;
	}

//getting a specific record by using the method findById() of CrudRepository  
	public Countries getCountriesByCode(int code) {
		return countriesRepository.findById(code).get();
	}

//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Countries countries) {
		countriesRepository.save(countries);
	}

//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(int code) {
		countriesRepository.deleteById(code);
	}

//updating a record  
	public void update(Countries countries, int countryCode) {
		countriesRepository.save(countries);
	}
	
	public List<Object[]> getAllBy() {
	    return countriesRepository.queryBy();
	    }
	
	public List<Object[]> getAllByCode(int code) {
	    return countriesRepository.queryBy2(code);
	    }
}
