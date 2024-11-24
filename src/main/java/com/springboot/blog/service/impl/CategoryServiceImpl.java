package com.springboot.blog.service.impl;

import com.springboot.blog.dto.CategoryDto;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Category;
import com.springboot.blog.repository.CatergoryRepository;
import com.springboot.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CatergoryRepository catergoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CatergoryRepository catergoryRepository, ModelMapper modelMapper) {
        this.catergoryRepository = catergoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = catergoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category = catergoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = catergoryRepository.findAll();
        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class)).toList();
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category = catergoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category","Id",categoryId));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        catergoryRepository.save(category);
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public String deleteCategoryById(Long categoryId) {
        Category category = catergoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category","Id",categoryId));
        catergoryRepository.delete(category);
        return "Deleted Successfully";
    }
}
