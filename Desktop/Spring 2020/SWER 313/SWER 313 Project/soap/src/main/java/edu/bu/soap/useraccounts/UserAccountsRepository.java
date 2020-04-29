package edu.bu.soap.useraccounts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountsRepository extends JpaRepository<UserAccounts, String> {
	Optional<UserAccounts> findByUserName(String UserName);

}
