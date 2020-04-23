package edu.bu.soap.countries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


//mark class as Controller
@RestController
public class CountriesController {
//autowire the countrysService class
    @Autowired
    CountriesService countriesService;
   /* @Autowired
    InfectionsService infectionsService;
    @Autowired
    UserAccountsService userAccountsService;*/
    

//creating a get mapping that retrieves all the countries detail from the database
    @GetMapping("/countries")
    private String getAllCountries() {
    	 ArrayList<CountriesInfections> map = new ArrayList<>();
    	 Gson gson = new GsonBuilder().setPrettyPrinting().create();
      
    	List<Object[]> list= countriesService.getAllBy();
    	for (Object[] var : list) {
    		String name= (String) var[1];
    		int code = (int) var[0];
    		int deaths = (int) var[2];
    		int infections = (int) var[3];
    		int recoveries = (int) var[4];
    		
    		CountriesInfections record = new CountriesInfections(code,name,deaths,infections,recoveries);
    		map.add(record);
    	}
    	 String  result = gson.toJson(map);
        return result;
    }

//creating a get mapping that retrieves the detail of a specific country
    @GetMapping("/country/{countryCode}")
    private String getCountries(@PathVariable("countryCode") int countryCode) {
    //	countriesService.getAllByCode(countryCode)
    	 ArrayList<CountriesInfections> map = new ArrayList<>();
    	 Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	List<Object[]> list= countriesService.getAllByCode(countryCode);
    	for (Object[] var : list) {
    		String name= (String) var[1];
    		int code = (int) var[0];
    		int deaths = (int) var[2];
    		int infections = (int) var[3];
    		int recoveries = (int) var[4];
    		
    		CountriesInfections record = new CountriesInfections(code,name,deaths,infections,recoveries);
    		map.add(record);
    	}
    	 String  result = gson.toJson(map);

        return result;
    }

//creating a delete mapping that deletes a specified country
    @DeleteMapping("/country/{countryCode}")
    private void deleteCountry(@PathVariable("countryCode") int countryCode) {
    	countriesService.delete(countryCode);
    }

//creating post mapping that post the country detail in the database
    @PostMapping("/countries")
    private int saveCountry(@RequestBody Countries countries) {
    	countriesService.saveOrUpdate(countries);
        return countries.getCountryCode();
    }

//creating put mapping that updates the country detail
    @PutMapping("/countries")
    private Countries update(@RequestBody Countries countries) {
    	countriesService.saveOrUpdate(countries);
        return countries;
    }
}