package edu.bu.soap;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registerUser(
                @RequestParam("userName") String name,
                @RequestParam("userPassword") String password,
                @RequestParam("email") String email,
                @RequestParam("birthDate") Date birthDate,
                @RequestParam("userPhoto") MultipartFile file,
                HttpServletRequest request,
                HttpServletResponse response) {
    
    	byte[] photo=null;
    	if(!file.isEmpty()) {
    	try {
    			photo = file.getBytes();
 
    		} catch (IOException e) {
    			
    			System.out.println("error loading file");
    		}
    	}
    	password = new SecurityConfiguration().getPasswordEncoder().encode(password);
    	UserAccounts userAccounts = new UserAccounts(name,password,birthDate,photo,email);
    	userAccountsService.saveOrUpdate(userAccounts);
    	
    	return new ResponseEntity("You have been registred successfully " + userAccounts.getUserName() + "!!",HttpStatus.CREATED);
       // return userAccounts.getUserName();
    }

//creating put mapping that updates the userAccount detail
    @PutMapping("/changepassword/{userAccountUserName}")
    private void updatePassword(@PathVariable("userAccountUserName") String userAccountUserName, @RequestBody String password) {
    	UserAccounts userAccount = userAccountsService.getUserAccountsByUserName(userAccountUserName);
    	userAccount.setUserPassword(password);
    	userAccountsService.saveOrUpdate(userAccount);
    }
    
  //creating put mapping that updates the userAccount detail
    @PutMapping("/changeemail/{userAccountUserName}")
    private void updateEmail(@PathVariable("userAccountUserName") String userAccountUserName, @RequestBody String email) {
    	UserAccounts userAccount = userAccountsService.getUserAccountsByUserName(userAccountUserName);
    	userAccount.setEmail(email);
    	userAccountsService.saveOrUpdate(userAccount);
    }
    
    //creating put mapping that updates the userAccount detail
    @PutMapping("/changeuserphoto/{userAccountUserName}")
    private ResponseEntity updateUserPhoto(@PathVariable("userAccountUserName") String userAccountUserName, @RequestParam("userPhoto") MultipartFile file) {
    	UserAccounts userAccount = userAccountsService.getUserAccountsByUserName(userAccountUserName);
    	byte[] photo;
		try {
			photo = file.getBytes();
			userAccount.setUserPhoto(photo);
			userAccountsService.saveOrUpdate(userAccount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        /*String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
	        		.path(file.getOriginalFilename())
	                .toUriString();*/
	       return new ResponseEntity("Photo is uploaded successfully!!", HttpStatus.OK);
	      // return new ResponseEntity("",HttpStatus.OK);
    }
    
  //creating put mapping that updates the userAccount detail
    @PutMapping("/changebirthdate/{userAccountUserName}")
    private void updateBirthDate(@PathVariable("userAccountUserName") String userAccountUserName, @RequestBody Date birthDate) {
    	UserAccounts userAccount = userAccountsService.getUserAccountsByUserName(userAccountUserName);
    	userAccount.setBirthDate(birthDate);
    	userAccountsService.saveOrUpdate(userAccount);
    }
    

}
