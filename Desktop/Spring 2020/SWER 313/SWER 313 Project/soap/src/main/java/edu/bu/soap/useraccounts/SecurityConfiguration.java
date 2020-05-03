package edu.bu.soap.useraccounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;

	// gives authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	// give and prevent permissions for users
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/user").hasAnyRole("ADMIN", "USER")
				.antMatchers("/").permitAll().antMatchers(HttpMethod.POST, "/registration").permitAll()
				.antMatchers(HttpMethod.POST, "/**").hasRole("USER").antMatchers(HttpMethod.PUT, "/**").hasRole("USER")
				.antMatchers(HttpMethod.DELETE, "/**").hasRole("USER")

				.and().cors().and().csrf().disable()
				.formLogin();
       //  .oauth2Login();
	}

	// encode password to not store it as a plain text (more secure)
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

@EnableWebSecurity
@Configuration
@Order(2)
class SecurityConfigurationGoogle extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;

	// gives authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	// give and prevent permissions for users
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/user").hasAnyRole("ADMIN", "USER")
				.antMatchers("/").permitAll().antMatchers(HttpMethod.POST, "/registration").permitAll()
				.antMatchers(HttpMethod.POST, "/**").hasRole("USER").antMatchers(HttpMethod.PUT, "/**").hasRole("USER")
				.antMatchers(HttpMethod.DELETE, "/**").hasRole("USER")

				.and().cors().and().csrf().disable()
				//.formLogin();
        .oauth2Login();
	}

	

}

