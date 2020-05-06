package edu.bu.soap.useraccounts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**defining the business logic of the user accounts services*/ 
@Service
@Scope("singleton")
public class UserAccountsService {

	@Autowired
	UserAccountsRepository userAccountsRepository;

	/**getting all userAccounts record by using the method findaAll() of
	 CrudRepository*/
	public List<UserAccounts> getAllUserAccounts() {
		List<UserAccounts> userAccounts = new ArrayList<UserAccounts>();
		userAccountsRepository.findAll().forEach(userAccounts1 -> userAccounts.add(userAccounts1));
		return userAccounts;
	}

	/**getting a specific record by using the method findById() of JpaRepository*/
	public UserAccounts getUserAccountsByUserName(String userName) {
		return userAccountsRepository.findById(userName).get();
	}

	/**saving a specific record by using the method save() of JpaRepository*/
	public void saveOrUpdate(UserAccounts userAccounts) {
		userAccountsRepository.save(userAccounts);
	}

	/**deleting a specific record by using the method deleteById() of JpaRepository*/
	public void delete(String userName) {
		userAccountsRepository.deleteById(userName);
	}

	/**updating a user account's record*/
	public void update(UserAccounts userAccounts, String userAccountUserName) {
		userAccountsRepository.save(userAccounts);
	}
}
