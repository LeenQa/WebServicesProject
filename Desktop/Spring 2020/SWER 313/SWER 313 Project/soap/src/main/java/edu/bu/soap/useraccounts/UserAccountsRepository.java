package edu.bu.soap.useraccounts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountsRepository extends JpaRepository<UserAccounts, String> {
	Optional<UserAccounts> findByUserName(String UserName);

}
