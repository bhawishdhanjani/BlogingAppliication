package com.bhawish.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhawish.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
