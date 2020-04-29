package edu.bu.soap.countries;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    		int deaths = ((BigDecimal) var[2]).intValue();
    		int infections = ((BigDecimal) var[3]).intValue();
    		int recoveries = ((BigDecimal) var[4]).intValue();
    		
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
    	// ArrayList<CountriesInfections> map = new ArrayList<>();
    	try {
    	 Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	List<Object[]> list= countriesService.getAllByCode(countryCode);
    	CountriesInfections record = null;
    	for (Object[] var : list) {
    		String name= (String) var[1];
    		int code = (int) var[0];
    		int deaths = ((BigDecimal) var[2]).intValue();
    		int infections = ((BigDecimal) var[3]).intValue();
    		int recoveries = ((BigDecimal) var[4]).intValue();
    		record = new CountriesInfections(code,name,deaths,infections,recoveries);
    	}
    
    	 String  result = gson.toJson(record);

        return result; }
    	catch(Exception e) {
    		return "Make sure you entered a right id";
    	}
    	//return countriesService.getAllByCode(countryCode);
    }
    
//creating a delete mapping that deletes a specified country
    @DeleteMapping("/country/{countryCode}")
    private ResponseEntity<String> deleteCountry(@PathVariable("countryCode") int countryCode) {
    	try {
    	countriesService.delete(countryCode);
    	return new ResponseEntity<String>("The record have been deleted.", HttpStatus.OK);
    	} 
    	catch(Exception e) {
    		return new ResponseEntity<String>("Make sure you entered a right id", HttpStatus.BAD_REQUEST);
    	}
    }

   
    @GetMapping("/**")
    private ResponseEntity<String> getwrongPath() {
    	return new ResponseEntity<String>("You requested a wrong path.", HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/**")
    private ResponseEntity<String> postwrongPath() {
    	return new ResponseEntity<String>("You requested a wrong path.", HttpStatus.BAD_REQUEST);
    }
    
    @PutMapping("/**")
    private ResponseEntity<String> putwrongPath() {
    	return new ResponseEntity<String>("You requested a wrong path.", HttpStatus.BAD_REQUEST);
    }
    
    @DeleteMapping("/**")
    private ResponseEntity<String> deletewrongPath() {
    	return new ResponseEntity<String>("You requested a wrong path.", HttpStatus.BAD_REQUEST);
    }
    
    

//creating post mapping that post the country detail in the database
    @PostMapping("/countries")
    private ResponseEntity<String> saveCountry(@RequestBody Countries countries) {
    	try {
    	countriesService.saveOrUpdate(countries);
    	return new ResponseEntity<String>("New country record is added successfully!", HttpStatus.OK);
    	}
    	catch(Exception e2) {
    		return new ResponseEntity<String>("Make sure you entered correct data", HttpStatus.BAD_REQUEST);
    	}
    }

//creating put mapping that updates the country detail
   /* @PutMapping("/countries")
    private Countries update(@RequestBody Countries countries) {
    	countriesService.saveOrUpdate(countries);
        return countries;
    }*/
    
}