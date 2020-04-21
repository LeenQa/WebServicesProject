package edu.bu.soap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountsRepository extends JpaRepository<UserAccounts, String> {

}
