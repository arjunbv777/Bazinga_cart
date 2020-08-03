package com.bazinga.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bazinga.modal.User;
import com.bazinga.modal.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByUsername(userName);

		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

		return user.map(UserDetailsImpl::new).get();
	}

}