package com.bhawish.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhawish.blog.entities.Category;
import com.bhawish.blog.entities.Post;
import com.bhawish.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String keyword);
	
	

}
