
package com.bhawish.blog.payload;

import lombok.Data;


@Data
public class JwtAuthResponse {
	private String token;
	private UserDto user;

}
