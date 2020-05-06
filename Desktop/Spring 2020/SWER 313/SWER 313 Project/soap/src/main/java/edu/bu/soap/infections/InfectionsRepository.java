package edu.bu.soap.infections;

import org.springframework.data.repository.CrudRepository;

/**Infections repository that extends Crud repository*/
public interface InfectionsRepository extends CrudRepository<Infections, Integer> {
}
