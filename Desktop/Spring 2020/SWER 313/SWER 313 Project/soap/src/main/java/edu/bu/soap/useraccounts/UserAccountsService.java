package edu.bu.soap.useraccounts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

//defining the business logic
@Service
public class UserAccountsService {

	@Autowired
	UserAccountsRepository userAccountsRepository;
	
	//getting all userAccounts record by using the method findaAll() of CrudRepository  
		public List<UserAccounts> getAllUserAccounts() {
			List<UserAccounts> userAccounts = new ArrayList<UserAccounts>();
			userAccountsRepository.findAll().forEach(userAccounts1 -> userAccounts.add(userAccounts1));
			return userAccounts;
		}
		

	//getting a specific record by using the method findById() of CrudRepository  
		public UserAccounts getUserAccountsByUserName(String userName) {
			return userAccountsRepository.findById(userName).get();
		}
		
		public void changeUserName(String userName, String userName1) {
			userAccountsRepository.findById(userName).get().setUserName(userName1);
		}


	//saving a specific record by using the method save() of CrudRepository  
		public void saveOrUpdate(UserAccounts userAccounts) {
			userAccountsRepository.save(userAccounts);
		}

	//deleting a specific record by using the method deleteById() of CrudRepository  
		public void delete(String userName) {
			userAccountsRepository.deleteById(userName);
		}

	//updating a record  
		public void update(UserAccounts userAccounts, String userAccountUserName) {
			userAccountsRepository.save(userAccounts);
		}
		
		 public void storeFile(MultipartFile file) {
		    }
}
