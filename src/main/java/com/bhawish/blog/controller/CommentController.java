package com.bhawish.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhawish.blog.payload.ApiResponse;
import com.bhawish.blog.payload.CommentDto;
import com.bhawish.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/user/{userId}/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer userId ,@PathVariable Integer postId) {
		CommentDto createComment = commentService.createComment(commentDto, postId, userId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.OK);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		commentService.deleteComment(commentId);
		ApiResponse apiResponse = new ApiResponse("Seuccesfully Deleted Comment ", true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	

}
