package com.bhawish.blog.services;

import java.util.List;

import com.bhawish.blog.payload.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
	void deleteUser(Integer userId);
	
	
	

}
