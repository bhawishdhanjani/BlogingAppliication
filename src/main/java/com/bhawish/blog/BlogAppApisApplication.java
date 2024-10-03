package com.bhawish.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mapping.model.CamelCaseSplittingFieldNamingStrategy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bhawish.blog.config.AppConstants;
import com.bhawish.blog.entities.Role;
import com.bhawish.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner{
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}



	@Override
	public void run(String... args) throws Exception {
		try {
			Role adminRole = new Role();
			adminRole.setId(AppConstants.ADMIN_USER);
			adminRole.setName("ROLE_ADMIN");
			Role normalRole = new Role();
			normalRole.setId(AppConstants.NORMAL_USER);
			normalRole.setName("ROLE_USER");
			List<Role> roles = List.of(adminRole,normalRole);
			roleRepo.saveAll(roles);
			String n = "Pakistan";
			
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	

}
