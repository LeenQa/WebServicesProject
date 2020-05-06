package edu.bu.soap;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "edu.bu.soap.countries", "edu.bu.soap.infections", "edu.bu.soap.security",
		"edu.bu.soap.useraccounts" })

@EnableJpaRepositories({ "edu.bu.soap.countries", "edu.bu.soap.infections", "edu.bu.soap.security",
		"edu.bu.soap.useraccounts" })

public class SoapProjectApplication {
	public static void main(String[] args) {
		/**The main method used to run the application and its services*/
		SpringApplication.run(SoapProjectApplication.class, args);
	}
	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+3:00")); // change to Palestine's timezone
	}
}
