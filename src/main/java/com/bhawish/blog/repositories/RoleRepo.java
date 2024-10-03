package com.bhawish.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhawish.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
