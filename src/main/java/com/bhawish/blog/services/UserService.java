package com.bhawish.blog.services;

import java.util.List;

import com.bhawish.blog.entities.User;
import com.bhawish.blog.payload.UserDto;

public interface UserService {
	UserDto registerNewUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
	void deleteUser(Integer userId);
	UserDto getCurrentUser(String name);
	
	
	

}
