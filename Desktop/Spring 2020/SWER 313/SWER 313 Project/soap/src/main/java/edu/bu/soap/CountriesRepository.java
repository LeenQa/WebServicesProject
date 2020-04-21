package edu.bu.soap;
  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;  
//repository that extends CrudRepository  
public interface CountriesRepository extends CrudRepository<Countries, Integer> {  
	@Query(value = "SELECT countries.country_code, countries.country_name, infections.num_of_infections, infections.num_of_deaths, infections.num_of_recoveries FROM countries, infections WHERE countries.country_code = infections.the_country_code", nativeQuery = true)
	List<Object[]> queryBy();
	
	@Query(value = "SELECT countries.country_code, countries.country_name, infections.num_of_infections, infections.num_of_deaths, infections.num_of_recoveries FROM countries, infections WHERE countries.country_code=?1 AND countries.country_code = infections.the_country_code", nativeQuery = true )
	List<Object[]> queryBy2(int code);
	

}


