package edu.bu.soap.infections;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
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
    private List<Infections> getAllInfections() {
        return infectionsService.getAllInfections();
    }

//creating a get mapping that retrieves the detail of a specific infection
    @GetMapping("/infection/{infectionId}")
    private Infections getInfections(@PathVariable("infectionId") int infectionId) {
        return infectionsService.getInfectionsById(infectionId);
    }

//creating a delete mapping that deletes a specified infection
    @DeleteMapping("/infection/{infectionId}")
    private void deleteInfection(@PathVariable("infectionId") int infectionId) {
    	infectionsService.delete(infectionId);
    }

//creating post mapping that post the infection detail in the database
    @PostMapping("/infections")
    private int saveInfection(@RequestBody Infections infections) {
    	infectionsService.saveOrUpdate(infections);
        return infections.getId();
    }

//creating put mapping that updates the infection detail
    @PutMapping("/infections/changesourceurl/{infectionId}")
    private String updateSourceURL(@PathVariable("infectionId") int id, @RequestParam String sourceURL) {
    	Infections infection = infectionsService.getInfectionsById(id);
    	infection.setSourceURL(sourceURL);
    	
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
    		return "Source URL is changed successfully!";
    	} else return "You can only change your infections data in the same day you put it.";

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