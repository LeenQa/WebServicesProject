package edu.bu.soap.infections;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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

import edu.bu.soap.countries.CountriesService;

/**a controller class for infections that contains all the CRUD methods needed to implement the services for the infections*/
@RestController
public class InfectionsController {
//autowire the infectionsService class
	@Autowired
	InfectionsService infectionsService;
	@Autowired
	CountriesService countriesService;

/**creating a get mapping that retrieves all the infections details from the database that are made by the logged in user*/
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

//creating a delete mapping that deletes a specified infection that is made by the logged in user
	@DeleteMapping("/infections/deleteinfection/{infectionId}")
	private ResponseEntity<String> deleteInfection(@PathVariable("infectionId") int infectionId) {
		try {
			Infections infection = infectionsService.getInfectionsById(infectionId);
			LocalDateTime reported = infection.getDtReported();
			LocalDateTime today = LocalDateTime.now();
			org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String name = auth.getName();
			String reportedBy = infectionsService.getInfectionsById(infectionId).getReportedBy();
			if (name.equals(reportedBy)) {
				if (reported.getDayOfYear() == today.getDayOfYear() && today.getDayOfMonth() == reported.getDayOfMonth()
						&& reported.getDayOfWeek() == today.getDayOfWeek()) {
				infectionsService.delete(infectionId);
				} else return new ResponseEntity<String>("You can only delete the record on the same day you put it.", HttpStatus.BAD_REQUEST);
				return new ResponseEntity<String>("The reported infection has been deleted!", HttpStatus.OK);
			} else
				return new ResponseEntity<String>("You can't delete data you have not made!", HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			return new ResponseEntity<String>("Make sure to enter a right ifection ID.", HttpStatus.BAD_REQUEST);
		}
	}

//creating post mapping that post the infection detail in the database
	@PostMapping("/infections")
	private ResponseEntity<String> saveInfection(@RequestBody Infections infections) {
		try {
			if (!getCountryCodes().contains(Integer.valueOf(infections.getCountryCode()))) {
				return new ResponseEntity<String>("The country you are trying to add a record for is not present",
						HttpStatus.BAD_REQUEST);
			} else
				TimeZone.setDefault(TimeZone.getTimeZone("GMT+3:00"));
			String sourceURL = infections.getSourceURL();
			if (sourceURL.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
				org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext()
						.getAuthentication();
				infections.setReportedBy(auth.getName());
				LocalDateTime reported = LocalDateTime.now();
				infections.setDtReported(reported);

				infectionsService.saveOrUpdate(infections);
				return new ResponseEntity<String>(
						"Infection " + infections.getId() + " has been reported successfully! ", HttpStatus.OK);
			} else
				return new ResponseEntity<String>("Make sure to enter a right URL.", HttpStatus.BAD_REQUEST);
		} catch (Exception e2) {
			return new ResponseEntity<String>("Make sure you entered correct data", HttpStatus.BAD_REQUEST);
		}
	}

	// creating put mapping that changes the url of a specific infection in the same
	// day it was recorded in.
	@PutMapping("/infections/changesourceurl/{infectionId}")
	private ResponseEntity<String> updateSourceURL(@PathVariable("infectionId") int id,
			@RequestParam String sourceURL) {
		try {
			if (sourceURL.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
				Infections infection = infectionsService.getInfectionsById(id);
				infection.setSourceURL(sourceURL);

				LocalDateTime reported = infection.getDtReported();
				String reportedBy = infection.getReportedBy();
				LocalDateTime today = LocalDateTime.now();

				org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext()
						.getAuthentication();
				String name = auth.getName();

				if (!(name.equals(reportedBy))) {
					return new ResponseEntity<String>("You can't change data that you have not entered!",
							HttpStatus.BAD_REQUEST);
				} else if (reported.getDayOfYear() == today.getDayOfYear()
						&& today.getDayOfMonth() == reported.getDayOfMonth()
						&& reported.getDayOfWeek() == today.getDayOfWeek()) {
					infectionsService.saveOrUpdate(infection);
					return new ResponseEntity<String>("Source URL is changed successfully!", HttpStatus.OK);
				} else
					return new ResponseEntity<String>(
							"You can only change your infections data in the same day you put it.",
							HttpStatus.BAD_REQUEST);
			} else
				return new ResponseEntity<String>("Make sure to enter a right URL.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>("Make sure to enter a right ifection ID.", HttpStatus.BAD_REQUEST);
		}
	}

	// creating put mapping that updates the number of infections in a specific
	// infection in the same day it was recorded in.
	@PutMapping("/infections/numberofinfections/{infectionId}")
	private String updateNumOfInfections(@PathVariable("infectionId") int id, @RequestParam int infections) {
		Infections infection = infectionsService.getInfectionsById(id);
		infection.setNumOfInfections(infections);
		LocalDateTime reported = infection.getDtReported();
		String reportedBy = infection.getReportedBy();
		LocalDateTime today = LocalDateTime.now();

		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		if (!(name.equals(reportedBy))) {
			return "You can't change data that you have not entered!";
		} else if (reported.getDayOfYear() == today.getDayOfYear() && today.getDayOfMonth() == reported.getDayOfMonth()
				&& reported.getDayOfWeek() == today.getDayOfWeek()) {
			infectionsService.saveOrUpdate(infection);
			return "Number of infections is changed successfully!";
		} else
			return "You can only change your infections data in the same day you put it.";

	}

	// creating put mapping that updates the number of deaths in a specific
	// infection in the same day it was recorded in.
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
		} else if (reported.getDayOfYear() == today.getDayOfYear() && today.getDayOfMonth() == reported.getDayOfMonth()
				&& reported.getDayOfWeek() == today.getDayOfWeek()) {
			infectionsService.saveOrUpdate(infection);
			return "Number of deaths is changed successfully!";
		} else
			return "You can only change your infections data in the same day you put it.";

	}

	// creating put mapping that updates the number of recoveries in a specific
	// infection in the same day it was recorded in.
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
		} else if (reported.getDayOfYear() == today.getDayOfYear() && today.getDayOfMonth() == reported.getDayOfMonth()
				&& reported.getDayOfWeek() == today.getDayOfWeek()) {
			infectionsService.saveOrUpdate(infection);
			return "Number of recoveries is changed successfully!";
		} else
			return "You can only change your infections data in the same day you put it.";

	}

	/**method to get countries codes*/
	ArrayList<Integer> getCountryCodes() {
		ArrayList<Integer> countrycodes = new ArrayList<Integer>();
		List<Object[]> list = countriesService.getcountryCodes();
		for (Object[] var : list) {
			int code = (int) var[0];
			countrycodes.add(code);
		}
		return countrycodes;
	}
}