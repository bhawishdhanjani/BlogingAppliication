package com.bhawish.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhawish.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
