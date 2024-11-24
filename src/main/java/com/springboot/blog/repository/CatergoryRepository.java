package com.springboot.blog.repository;

import com.springboot.blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatergoryRepository extends JpaRepository<Category, Long> {
}
