package com.springboot.blog.repository;

import com.springboot.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository Optional
public interface PostRepository extends JpaRepository<Post, Long> {
    // Jpa Provides All CRUD Operations and supports for sorting and pagination
    List<Post> findByCategoryId(Long categoryId);
}
