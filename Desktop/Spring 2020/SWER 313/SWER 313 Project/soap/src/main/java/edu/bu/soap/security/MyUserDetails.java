package edu.bu.soap.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.bu.soap.useraccounts.UserAccounts;
/**defines the details of the logged in users*/
public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;

	/**get the user's username and password*/
	public MyUserDetails(UserAccounts userAccount) {
		this.userName = userAccount.getUserName();
		this.password = userAccount.getUserPassword();
	}

	/**define that the ROLE is always USER*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
