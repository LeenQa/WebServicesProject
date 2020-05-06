package edu.bu.soap.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**define the main paths of the application(main and login page)*/
@RestController
public class HomeResource {

	/**direct to the main page*/
	@GetMapping("/")
	public String home() {
		return ("<h1 style=\"text-align:center;color:purple;font-size:50px; border-bottom: 1px solid black\">Welcome to the main page<h1>");
	}

	/**direct user to the user page*/
	@GetMapping("/user")
	public String user() {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		
		return ("<h1 style=\"text-align:center;color:purple;font-size:50px; border-bottom: 1px solid black\">Welcome "
				+ name + "!!</h1>");
	}
}