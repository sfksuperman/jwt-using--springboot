package com.jwt.services;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		if (userName.equals("Faisal")) {
			return new User("Faisal", "Faisal123", new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User Not Found");
		}
	}

}
