package com.bhawish.blog.services.impl;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bhawish.blog.entities.Category;
import com.bhawish.blog.entities.Post;
import com.bhawish.blog.entities.User;
import com.bhawish.blog.exceptions.ResourceNotFoundException;
import com.bhawish.blog.payload.PostDto;
import com.bhawish.blog.payload.PostResponce;
import com.bhawish.blog.repositories.CategoryRepo;
import com.bhawish.blog.repositories.PostRepo;
import com.bhawish.blog.repositories.UserRepo;
import com.bhawish.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	private PostRepo postRepo;
	private UserRepo userRepo;
	private CategoryRepo categoryRepo;
	private ModelMapper mapper;
	
	
	public PostServiceImpl(PostRepo postRepo, UserRepo userRepo, CategoryRepo categoryRepo,ModelMapper mapper) {
		super();
		this.postRepo = postRepo;
		this.userRepo = userRepo;
		this.categoryRepo = categoryRepo;
		this.mapper = mapper;
	}

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		Post post = dtoToPost(postDto);
		post.setAddedDate(new Date());
		post.setCategory(categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId)));
		post.setUser(userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId)));
		post.setImageName("default.jpg");
		
		Post savedPost = postRepo.save(post);
		return postToDto(savedPost);
	}

	@Override
	public PostDto updatePost(Integer postId, PostDto postDto) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		post.setImageName(post.getImageName());
		return postToDto(post);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		postRepo.delete(post);
		
		
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()->  new ResourceNotFoundException("Post", "Id", postId));
		return postToDto(post);
	}

	@Override
	public PostResponce getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pagePost = postRepo.findAll(pageable);
		List<Post> posts = pagePost.getContent();
		List<PostDto> postDtos = posts.stream().map((post)->postToDto(post)).collect(Collectors.toList());
		PostResponce postResponce = PostResponce.builder().content(postDtos).pageNumber(pagePost.getNumber()).pageSize(pagePost.getSize()).totalElements(pagePost.getTotalElements()).totalPages(pagePost.getTotalPages()).isLast(pagePost.isLast()).build();
		return postResponce ;
	}

	@Override
	public List<PostDto> getPostByUserId(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)-> postToDto(post)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByCategoryId(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> postdtos = posts.stream().map((post)->postToDto(post)).collect(Collectors.toList());
		return postdtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post)->postToDto(post)).collect(Collectors.toList());
		return postDtos;
	}
	
	public Post dtoToPost(PostDto postDto) {
		return mapper.map(postDto, Post.class);
		
	}
	public PostDto postToDto(Post post) {
		return mapper.map(post, PostDto.class);
		
	}

}
