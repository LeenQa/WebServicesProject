package edu.bu.soap.countries;
  
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;  
/**Countries repository that extends Crud repository*/
@Repository
public interface CountriesRepository extends CrudRepository<Countries, Integer> {  
	/**return a list of the countries with the number of infections, deaths and recoveries in each country*/
	@Query(value = "SELECT countries.country_code, countries.country_name, sum(infections.num_of_infections), sum(infections.num_of_deaths), sum(infections.num_of_recoveries) FROM countries, infections WHERE countries.country_code = infections.the_country_code GROUP BY countries.country_code", nativeQuery = true)
	List<Object[]> queryBy();
	
	/**return a list of the countries with the number of infections, deaths and recoveries for a specific country*/
	@Query(value = "SELECT countries.country_code, countries.country_name, sum(infections.num_of_infections), sum(infections.num_of_deaths), sum(infections.num_of_recoveries) FROM countries, infections WHERE countries.country_code=?1 AND countries.country_code = infections.the_country_code", nativeQuery = true )
	List<Object[]> queryBy2(int code);
	
	/**return a list of the country codes*/
		@Query(value = "SELECT country_code FROM countries", nativeQuery = true)
		List<Object[]> getCountryCodes();
	

}


