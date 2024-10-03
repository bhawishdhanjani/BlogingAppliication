package com.bhawish.blog.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhawish.blog.payload.ApiResponse;
import com.bhawish.blog.payload.UserDto;
import com.bhawish.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUserDto = userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);	
	}
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto ,   @PathVariable Integer userId){
		UserDto updateUser = userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updateUser);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId){
		userService.deleteUser(userId);
		return ResponseEntity.ok(new ApiResponse("User Deleted Successfully",true));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable int userId){
		UserDto userDto = userService.getUserById(userId);
		return ResponseEntity.ok(userDto);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}

}
