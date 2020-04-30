package edu.bu.soap.infections;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//defining the business logic  
@Service
public class InfectionsService {
	@Autowired
	InfectionsRepository infectionsRepository;

//getting all infections records by using the method findaAll() of CrudRepository  
	public List<Infections> getAllInfections() {
		List<Infections> infections = new ArrayList<Infections>();
		infectionsRepository.findAll().forEach(infections1 -> infections.add(infections1));
		return infections;
	}

//getting a specific record by using the method findById() of CrudRepository  
	public Infections getInfectionsById(int id) {
		return infectionsRepository.findById(id).get();
	}

//saving a specific infection record by using the method save() of CrudRepository  
	public void saveOrUpdate(Infections infections) {
		infectionsRepository.save(infections);
	}

//deleting a specific infection record by using the method deleteById() of CrudRepository  
	public void delete(int id) {
		infectionsRepository.deleteById(id);
	}

//updating a record  
	public void update(Infections infections, int infectionId) {
		infectionsRepository.save(infections);
	}
}
