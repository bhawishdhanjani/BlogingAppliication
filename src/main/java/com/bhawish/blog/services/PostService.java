package com.bhawish.blog.services;

import java.util.List;

import com.bhawish.blog.payload.PostDto;
import com.bhawish.blog.payload.PostResponce;

public interface PostService {
	PostDto createPost(PostDto postDto, Integer userId , Integer categoryId);
	PostDto updatePost(Integer postId, PostDto postDto);
	void deletePost(Integer postId);
	PostDto getPostById(Integer postId);
	PostResponce getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	List<PostDto> getPostByUserId(Integer userId);
	List<PostDto> getPostByCategoryId(Integer categoryId);
	List<PostDto> searchPosts(String keyword);
	
	
	

}
