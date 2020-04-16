package edu.bu.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//mark class as Controller
@RestController
public class UserAccountsController {
	//autowire the userAccountsService class
    @Autowired
    UserAccountsService userAccountsService;
   /* @Autowired
    InfectionsService infectionsService;
    @Autowired
    UserAccountsService userAccountsService;*/
    

//creating a get mapping that retrieves all the userAccounts detail from the database
    @GetMapping("/useraccounts")
    private List<UserAccounts> getAllUserAccounts() {
        return userAccountsService.getAllUserAccounts();
    }

//creating a get mapping that retrieves the detail of a specific userAccount
    @GetMapping("/useraccount/{userAccountUserName}")
    private UserAccounts getUserAccounts(@PathVariable("userAccountUserName") String userAccountUserName) {
        return userAccountsService.getUserAccountsByUserName(userAccountUserName);
    }

//creating a delete mapping that deletes a specified userAccount
    @DeleteMapping("/useraccount/{userAccountUserName}")
    private void deleteUserAccount(@PathVariable("userAccountUserName") String userAccountUserName) {
    	userAccountsService.delete(userAccountUserName);
    }

//creating post mapping that post the userAccount detail in the database
    @PostMapping("/useraccounts")
    private String saveUserAccount(@RequestBody UserAccounts userAccounts) {
    	userAccountsService.saveOrUpdate(userAccounts);
        return userAccounts.getUserName();
    }

//creating put mapping that updates the userAccount detail
    @PutMapping("/useraccounts")
    private UserAccounts update(@RequestBody UserAccounts userAccounts) {
    	userAccountsService.saveOrUpdate(userAccounts);
        return userAccounts;
    }
}
