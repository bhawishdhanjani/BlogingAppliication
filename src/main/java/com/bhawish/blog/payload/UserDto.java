package com.bhawish.blog.payload;


import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 4 , message = "Username Can Not be Less then 4 Character")
	private String name;
	
	@Email(message = "Email Address is not Valid")
	private String email;
	@NotEmpty
	@Size(min=3,max=10,message = "Password Length Must Be 3 to 10 character")
	private String password;
	private String about;
	
	private List<CommentDto> comments;


}
