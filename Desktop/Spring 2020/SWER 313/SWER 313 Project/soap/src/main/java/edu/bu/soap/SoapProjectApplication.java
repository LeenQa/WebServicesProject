package edu.bu.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import edu.bu.soap.useraccounts.UserAccountsRepository;

@SpringBootApplication(scanBasePackages={"edu.bu.soap.countries","edu.bu.soap.infections", "edu.bu.soap.security", "edu.bu.soap.useraccounts"})
//@EnableJpaRepositories(basePackageClasses = UserAccountsRepository.class)
@EnableJpaRepositories ({"edu.bu.soap.countries","edu.bu.soap.infections", "edu.bu.soap.security", "edu.bu.soap.useraccounts"})
//@ComponentScan({"edu.bu.soap.countries", "edu.bu.soap.infections"})
public class SoapProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapProjectApplication.class, args);
	}

}
