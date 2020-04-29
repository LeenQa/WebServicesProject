package edu.bu.soap.useraccounts;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	// autowire the userAccountsService class
	@Autowired
	UserAccountsService userAccountsService;
	/*
	 * @Autowired InfectionsService infectionsService;
	 * 
	 * @Autowired UserAccountsService userAccountsService;
	 */

//creating a get mapping that retrieves the userAccount details from the database
	@GetMapping("/myprofile")
	private String getAllUserAccounts() {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		return userAccountsService.getUserAccountsByUserName(name).toString();
	}

//creating a delete mapping that deletes a specified userAccount
	@DeleteMapping("/useraccount/{userAccountUserName}")
	private String deleteUserAccount(@PathVariable("userAccountUserName") String userAccountUserName) {
		try {
			org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String name = auth.getName();
			if (name.equals(userAccountUserName)) {
				userAccountsService.delete(userAccountUserName);
				return "Deleted successfully.";
			} else {
				return "You can't delete an account that is not yours";
			}
		} catch (Exception e) {
			return "Make sure to enter a right username.";
		}
	}

//creating post mapping that register a user and saves his account details in the database
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestParam("userName") String name,
			@RequestParam("userPassword") String password, @RequestParam("email") String email,
			@RequestParam("birthDate") Date birthDate, @RequestParam("userPhoto") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		byte[] photo = null;
		String fileName = null;
		if (!file.isEmpty()) {
			try {
				fileName = StringUtils.cleanPath(file.getOriginalFilename());
				photo = file.getBytes();

			} catch (IOException e) {

				System.out.println("error loading photo");
			}
		} else
			return new ResponseEntity<String>("Make sure to upload your photo.", HttpStatus.BAD_REQUEST);
		if (email.length() == 0 || name.length() == 0 || password.length() == 0 || birthDate == null) {
			return new ResponseEntity<String>("Make sure all field are filled.", HttpStatus.BAD_REQUEST);
		} else if (!name.matches("^[a-zA-Z0-9._-]{3,16}$")) {
			return new ResponseEntity<String>(
					"Username should be 4 letters minimum, 14 letters maximum. The letters must be alphanumeric.",
					HttpStatus.BAD_REQUEST);
		} else if (!password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,30}")) {
			return new ResponseEntity<String>(
					"Your password isn't strong enough. It should have at least one special character, one digit, one lowercase and one uppercase character, and it's length is 6 letters minimum, 16 letters maximum.",
					HttpStatus.BAD_REQUEST);
		} else if (!email.matches("^(.+)@(.+)$")) {
			return new ResponseEntity<String>("Make sure that you entered a valid email", HttpStatus.BAD_REQUEST);
		}
		password = new SecurityConfiguration().getPasswordEncoder().encode(password);
		UserAccounts userAccounts = new UserAccounts(name, password, birthDate, fileName, photo, email);
		userAccountsService.saveOrUpdate(userAccounts);

		return new ResponseEntity<String>("You have been registred successfully " + userAccounts.getUserName() + "!!",
				HttpStatus.CREATED);
		// return userAccounts.getUserName();
		// return fileDownloadUri;
	}

//creating put mapping that updates the user account's password
	@PutMapping("/useraccounts/changepassword/{userAccountUserName}")
	private ResponseEntity<String> updatePassword(@PathVariable("userAccountUserName") String userAccountUserName,
			@RequestBody String password) {
		try {
			org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			if (name.equals(userAccountUserName)) {
			UserAccounts userAccount = userAccountsService.getUserAccountsByUserName(userAccountUserName);
			if (!password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,30}")) {
				return new ResponseEntity<String>(
						"Your password isn't strong enough. It should have at least one special character, one digit, one lowercase and one uppercase character, and it's length is 6 letters minimum, 16 letters maximum.",
						HttpStatus.BAD_REQUEST);
			} else {
				userAccount.setUserPassword(password);
				userAccountsService.saveOrUpdate(userAccount);
				return new ResponseEntity<String>("You have changed your password successfully!", HttpStatus.OK);
			}
			}
			else 
				return new ResponseEntity<String>("Make sure to enter your right username.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>("Make sure to enter your right username.", HttpStatus.BAD_REQUEST);
		}
	}

	// creating put mapping that updates the user account's email
	@PutMapping("/useraccounts/changeemail/{userAccountUserName}")
	private ResponseEntity<String> updateEmail(@PathVariable("userAccountUserName") String userAccountUserName,
			@RequestBody String email) {
		try {
			org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			if (name.equals(userAccountUserName)) {
			UserAccounts userAccount = userAccountsService.getUserAccountsByUserName(userAccountUserName);
			if (!email.matches("^(.+)@(.+)$")) {
				return new ResponseEntity<String>("Make sure that you entered a valid email", HttpStatus.BAD_REQUEST);
			} else {
				userAccount.setEmail(email);
				userAccountsService.saveOrUpdate(userAccount);
				return new ResponseEntity<String>("Email changed successfully to " + email, HttpStatus.OK);
			}
			}
			else 
				return new ResponseEntity<String>("Make sure to enter your right username.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>("Make sure to enter your right username.", HttpStatus.BAD_REQUEST);
		}
	}

	// creating put mapping that updates the user account's photo detail
	@PutMapping("/useraccounts/changeuserphoto/{userAccountUserName}")
	private ResponseEntity<String> updateUserPhoto(@PathVariable("userAccountUserName") String userAccountUserName,
			@RequestParam("userPhoto") MultipartFile file) {
		try {
			org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			if (name.equals(userAccountUserName)) {
			UserAccounts userAccount = userAccountsService.getUserAccountsByUserName(userAccountUserName);
			byte[] photo;
			try {
				photo = file.getBytes();

				userAccount.setUserPhoto(photo);
				userAccountsService.saveOrUpdate(userAccount);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return new ResponseEntity<String>("Photo is uploaded successfully!!", HttpStatus.OK);
			}
			else 
				return new ResponseEntity<String>("Make sure to enter your right username.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>("Make sure to enter your right username.", HttpStatus.BAD_REQUEST);
		}
	}

	// creating put mapping that updates the user account's birth date
	@PutMapping("/useraccounts/changebirthdate/{userAccountUserName}")
	private ResponseEntity<String> updateBirthDate(@PathVariable("userAccountUserName") String userAccountUserName,
			@RequestBody String birthDate) throws ParseException {
		try {
			org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			if (name.equals(userAccountUserName)) {
			TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // to get the accurate date
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = (java.util.Date) df.parse(birthDate);

			UserAccounts userAccount = userAccountsService.getUserAccountsByUserName(userAccountUserName);
			userAccount.setBirthDate(date);
			userAccountsService.saveOrUpdate(userAccount);
			return new ResponseEntity<String>("You have changed your birth date successfully!", HttpStatus.OK);
			}
			else 
				return new ResponseEntity<String>("Make sure to enter your right username.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>("Make sure to enter a right username and a right date format.",
					HttpStatus.BAD_REQUEST);
		}
	}

}
