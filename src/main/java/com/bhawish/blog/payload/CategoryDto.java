package com.bhawish.blog.payload;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer categoryId;
	@NonNull
	@Size(min = 4, message = "Minimum Size of category title is 4")
	private String categoryTitle;
	@NonNull
	@Size(min = 10 , message = "Minimum Size of category description is 4")
	private String categoryDescription;
	
}
