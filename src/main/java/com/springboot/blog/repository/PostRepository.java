package com.springboot.blog.repository;

import com.springboot.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository Optional
public interface PostRepository extends JpaRepository<Post, Long> {
    // Jpa Provides All CRUD Operations and supports for sorting and pagination
}
