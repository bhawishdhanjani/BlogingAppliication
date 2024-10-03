package com.bhawish.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bhawish.blog.entities.Comment;
import com.bhawish.blog.entities.Post;
import com.bhawish.blog.entities.User;
import com.bhawish.blog.exceptions.ResourceNotFoundException;
import com.bhawish.blog.payload.CommentDto;
import com.bhawish.blog.repositories.CommentRepo;
import com.bhawish.blog.repositories.PostRepo;
import com.bhawish.blog.repositories.UserRepo;
import com.bhawish.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	private CommentRepo commentRepo;
	private PostRepo postRepo;
	private UserRepo userRepo;
	private ModelMapper modelMapper;
	
	
	
	public CommentServiceImpl(CommentRepo commentRepo, PostRepo postRepo, UserRepo userRepo, ModelMapper modelMapper) {
		super();
		this.commentRepo = commentRepo;
		this.postRepo = postRepo;
		this.userRepo = userRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id",postId));
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		Comment commet = modelMapper.map(commentDto, Comment.class);
		commet.setPost(post);
		commet.setUser(user);
		Comment savedCommennt = commentRepo.save(commet);
		return modelMapper.map(savedCommennt, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentId));
		commentRepo.delete(comment);		
	}

}
