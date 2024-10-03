package com.bhawish.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bhawish.blog.entities.User;
import com.bhawish.blog.exceptions.ResourceNotFoundException;
import com.bhawish.blog.repositories.UserRepo;


@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User ", "Email :"+username, 0));
		return user;
	}

}
