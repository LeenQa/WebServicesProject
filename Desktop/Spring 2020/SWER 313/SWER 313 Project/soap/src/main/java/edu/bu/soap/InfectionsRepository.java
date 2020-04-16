package edu.bu.soap;
  
import org.springframework.data.repository.CrudRepository;  
//repository that extends CrudRepository  
public interface InfectionsRepository extends CrudRepository<Infections, Integer> {  
}  

