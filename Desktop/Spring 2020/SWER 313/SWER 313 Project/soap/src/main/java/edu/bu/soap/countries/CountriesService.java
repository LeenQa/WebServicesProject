package edu.bu.soap.countries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


/**defining the business logic of the countries services*/  
@Service
@Scope("singleton")
public class CountriesService {
	@Autowired
	CountriesRepository countriesRepository;

/**getting all countries record by using the method findaAll() of CrudRepository*/
	public List<Countries> getAllCountries() {
		List<Countries> countries = new ArrayList<Countries>();
		countriesRepository.findAll().forEach(countries1 -> countries.add(countries1));
		return countries;
	}

/**getting a specific record by using the method findById() of CrudRepository*/
	public Countries getCountriesByCode(int code) {
		return countriesRepository.findById(code).get();
	}

/**saving a specific record by using the method save() of CrudRepository*/  
	public void saveOrUpdate(Countries countries) {
		countriesRepository.save(countries);
	}

/**deleting a specific record by using the method deleteById() of CrudRepository*/  
	public void delete(int code) {
		countriesRepository.deleteById(code);
	}

/**updating a country record*/
	public void update(Countries countries, int countryCode) {
		countriesRepository.save(countries);
	}
	/**returns a list of all countries with the number of infections,deaths and recoveries for the countries*/
	public List<Object[]> getAllBy() {
	    return countriesRepository.queryBy();
	    }
	/**returns a list of a specific country with the number of infections,deaths and recoveries for the country*/
	public List<Object[]> getAllByCode(int code) {
	    return countriesRepository.queryBy2(code);
	    }
	/**returns a list of all country codes*/
	public List<Object[]> getcountryCodes() {
	    return countriesRepository.getCountryCodes();
	    }
}
