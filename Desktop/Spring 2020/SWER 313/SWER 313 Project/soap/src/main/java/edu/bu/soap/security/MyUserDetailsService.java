package edu.bu.soap.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.bu.soap.useraccounts.UserAccounts;
import edu.bu.soap.useraccounts.UserAccountsRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserAccountsRepository userAccountsRepository;

	// return the logged in user's account
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserAccounts> user = userAccountsRepository.findByUserName(username);

		return user.map(MyUserDetails::new).get();
	}

}
