package edu.bu.soap;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
    private List<Countries> getAllCountries() {
        return countriesService.getAllCountries();
    }

//creating a get mapping that retrieves the detail of a specific country
    @GetMapping("/country/{countryCode}")
    private Countries getCountries(@PathVariable("countryCode") int countryCode) {
        return countriesService.getCountriesByCode(countryCode);
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