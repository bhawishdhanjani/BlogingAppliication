package com.bhawish.blog.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bhawish.blog.config.AppConstants;
import com.bhawish.blog.entities.Role;
import com.bhawish.blog.entities.User;
import com.bhawish.blog.exceptions.ResourceNotFoundException;
import com.bhawish.blog.payload.UserDto;
import com.bhawish.blog.repositories.RoleRepo;
import com.bhawish.blog.repositories.UserRepo;
import com.bhawish.blog.services.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;
	
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = dtoToUser(userDto);
		User savedUser =  userRepo.save(user);	
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "ID", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return userToDto(user);
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "ID", id));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<UserDto> usersDto = userRepo.findAll().stream().map((user)->userToDto(user)).collect(Collectors.toList());
		return usersDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "ID", userId));
		userRepo.delete(user);

	}
	
	public User dtoToUser(UserDto userDto) {
		return this.mapper.map(userDto, User.class);
		
	}
	
	
	public UserDto userToDto(User user) {
		return this.mapper.map(user, UserDto.class);
		
		
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = dtoToUser(userDto);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User savedUser = userRepo.save(user);
		return userToDto(savedUser);
	}

	@Override
	public UserDto getCurrentUser(String name) {
		User user = userRepo.findByEmail(name).get();
		return userToDto(user);
	}
	

	

}
