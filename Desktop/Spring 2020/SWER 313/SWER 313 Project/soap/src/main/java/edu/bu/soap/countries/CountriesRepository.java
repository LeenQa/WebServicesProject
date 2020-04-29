package edu.bu.soap.countries;
  
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;  
//repository that extends CrudRepository  
@Repository
public interface CountriesRepository extends CrudRepository<Countries, Integer> {  
	@Query(value = "SELECT countries.country_code, countries.country_name, sum(infections.num_of_infections), sum(infections.num_of_deaths), sum(infections.num_of_recoveries) FROM countries, infections WHERE countries.country_code = infections.the_country_code GROUP BY countries.country_code", nativeQuery = true)
	List<Object[]> queryBy();
	
	@Query(value = "SELECT countries.country_code, countries.country_name, sum(infections.num_of_infections), sum(infections.num_of_deaths), sum(infections.num_of_recoveries) FROM countries, infections WHERE countries.country_code=?1 AND countries.country_code = infections.the_country_code", nativeQuery = true )
	List<Object[]> queryBy2(int code);
	

}


