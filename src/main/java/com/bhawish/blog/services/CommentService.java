package com.bhawish.blog.services;

import com.bhawish.blog.payload.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
	void deleteComment(Integer commentId);

}
