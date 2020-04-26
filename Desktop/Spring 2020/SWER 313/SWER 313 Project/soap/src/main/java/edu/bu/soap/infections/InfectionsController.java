package edu.bu.soap.infections;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//mark class as Controller
@RestController
public class InfectionsController {
//autowire the infectionsService class
    @Autowired
    InfectionsService infectionsService;
   /* @Autowired
    InfectionsService infectionsService;
    @Autowired
    UserAccountsService userAccountsService;*/
    

//creating a get mapping that retrieves all the infections detail from the database
    @GetMapping("/infections")
    private ArrayList<Infections> getAllInfections() {
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String name = auth.getName();
    	ArrayList<Infections> allInfections = (ArrayList<Infections>) infectionsService.getAllInfections();
    	ArrayList<Infections> infections = new ArrayList<>();
    	
    	for (int i = 0; i < allInfections.size(); i++) {
    		if (allInfections.get(i).getReportedBy().equals(name)) {
    			infections.add(allInfections.get(i));
    		}
    	}
        return infections;
    }

//creating a delete mapping that deletes a specified infection
    @DeleteMapping("/infection/{infectionId}")
    private ResponseEntity<String> deleteInfection(@PathVariable("infectionId") int infectionId) {
    	try {
    	org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String name = auth.getName();
    	String reportedBy = infectionsService.getInfectionsById(infectionId).getReportedBy();
    	if (name.equals(reportedBy)) {
    		infectionsService.delete(infectionId);
    		return new ResponseEntity<String>( "The reported infection has been deleted!", HttpStatus.OK);
    	}
<<<<<<< HEAD
    	else 
    	return new ResponseEntity<String>("You can't delete data you have not made!", HttpStatus.BAD_REQUEST);
    	
    	} catch(Exception e) {
			return new ResponseEntity<String>("Make sure to enter a right ifection ID.", HttpStatus.BAD_REQUEST);
		}
=======
	 
    		return "You can't delete data you have not made!";
>>>>>>> 3e26133476b504da63bb67885114aee2cbd5c578
    }

//creating post mapping that post the infection detail in the database
    @PostMapping("/infections")
<<<<<<< HEAD
    private ResponseEntity<String> saveInfection(@RequestBody Infections infections) {
    	try {
    		String sourceURL = infections.getSourceURL();
    		if(sourceURL.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
=======
    private int saveInfection(@RequestBody Infections infections) {
    	org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	infections.setReportedBy(auth.getName());
    	LocalDateTime reported = LocalDateTime.now();
    	infections.setDtReported(reported);
>>>>>>> 3e26133476b504da63bb67885114aee2cbd5c578
    	infectionsService.saveOrUpdate(infections);
        return new ResponseEntity<String>("Infection" + infections.getId() +" has been reported successfully! ", HttpStatus.OK);
    		} else return new ResponseEntity<String>("Make sure to enter a right URL.", HttpStatus.BAD_REQUEST);
    }
	catch(Exception e2) {
		return new ResponseEntity<String>("Make sure you entered correct data", HttpStatus.BAD_REQUEST);
	}
    }

//creating put mapping that updates the infection detail
    @PutMapping("/infections/changesourceurl/{infectionId}")
    private ResponseEntity<String> updateSourceURL(@PathVariable("infectionId") int id, @RequestParam String sourceURL) {
    	try {
if(sourceURL.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
	Infections infection = infectionsService.getInfectionsById(id);
	infection.setSourceURL(sourceURL);
	
	LocalDateTime reported = infection.getDtReported();
	String reportedBy = infection.getReportedBy();
	LocalDateTime today = LocalDateTime.now();

	org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String name = auth.getName();
	
	if (!(name.equals(reportedBy))) {
		return new ResponseEntity<String>("You can't change data that you have not entered!", HttpStatus.BAD_REQUEST);
	}
	else if(reported.getDayOfYear()==today.getDayOfYear()&& today.getDayOfMonth()==reported.getDayOfMonth() 
			&& reported.getDayOfWeek()==today.getDayOfWeek()) {
		infectionsService.saveOrUpdate(infection);
		return new ResponseEntity<String>("Source URL is changed successfully!", HttpStatus.OK);
	} else return new ResponseEntity<String>("You can only change your infections data in the same day you put it.", HttpStatus.BAD_REQUEST);	
} else return new ResponseEntity<String>("Make sure to enter a right URL.", HttpStatus.BAD_REQUEST);
    	} catch(Exception e) {
			return new ResponseEntity<String>("Make sure to enter a right ifection ID.", HttpStatus.BAD_REQUEST);
		}
    }
    
    @PutMapping("/infections/numberofinfections/{infectionId}")
    private String updateNumOfInfections(@PathVariable("infectionId") int id, @RequestParam int infections) {
    	Infections infection = infectionsService.getInfectionsById(id);
    	infection.setNumOfInections(infections);
    	LocalDateTime reported = infection.getDtReported();
    	String reportedBy = infection.getReportedBy();
    	LocalDateTime today = LocalDateTime.now();
 
    	org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String name = auth.getName();
    	
    	if (!(name.equals(reportedBy))) {
    		return "You can't change data that you have not entered!";
    	}
    	else if(reported.getDayOfYear()==today.getDayOfYear()&& today.getDayOfMonth()==reported.getDayOfMonth() 
    			&& reported.getDayOfWeek()==today.getDayOfWeek()) {
    		infectionsService.saveOrUpdate(infection);
    		return "Number of infections is changed successfully!";
    	} else return "You can only change your infections data in the same day you put it.";
    	
    }
    
    @PutMapping("/infections/numberofdeaths/{infectionId}")
    private String updateNumOfDeaths(@PathVariable("infectionId") int id, @RequestParam int deaths) {
    	Infections infection = infectionsService.getInfectionsById(id);
    	infection.setNumOfDeaths(deaths);
    	LocalDateTime reported = infection.getDtReported();
    	String reportedBy = infection.getReportedBy();
    	LocalDateTime today = LocalDateTime.now();
 
    	org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String name = auth.getName();
    	
    	if (!(name.equals(reportedBy))) {
    		return "You can't change data that you have not entered!";
    	}
    	else if(reported.getDayOfYear()==today.getDayOfYear()&& today.getDayOfMonth()==reported.getDayOfMonth() 
    			&& reported.getDayOfWeek()==today.getDayOfWeek()) {
    		infectionsService.saveOrUpdate(infection);
    		return "Number of deaths is changed successfully!";
    	} else return "You can only change your infections data in the same day you put it.";
    	
    }
    
    @PutMapping("/infections/numberofrecoveries/{infectionId}")
    private String updateNumOfRecoveries(@PathVariable("infectionId") int id, @RequestParam int recoveries) {
    	Infections infection = infectionsService.getInfectionsById(id);
    	infection.setNumOfRecoveries(recoveries);
    	LocalDateTime reported = infection.getDtReported();
    	String reportedBy = infection.getReportedBy();
    	LocalDateTime today = LocalDateTime.now();
 
    	org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String name = auth.getName();
    	
    	if (!(name.equals(reportedBy))) {
    		return "You can't change data that you have not entered!";
    	}
    	else if(reported.getDayOfYear()==today.getDayOfYear()&& today.getDayOfMonth()==reported.getDayOfMonth() 
    			&& reported.getDayOfWeek()==today.getDayOfWeek()) {
    		infectionsService.saveOrUpdate(infection);
    		return "Number of recoveries is changed successfully!";
    	} else return "You can only change your infections data in the same day you put it.";
    	
    }
}