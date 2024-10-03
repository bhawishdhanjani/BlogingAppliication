package com.bhawish.blog.payload;

import lombok.Data;

@Data
public class JWTAuthRequest {
	private String username;
	private String password;

}
