package com.springboot.blog.service;

import com.springboot.blog.controller.request.CategoryCreateRequest;
import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    private final ModelMapper mapper;

    public CategoryService(CategoryRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Category create(CategoryCreateRequest request) {
        return repository.save(request.toEntity());
    }

    public CategoryDto getCategory(Long categoryId) {
        Category category = repository.getReferenceById(categoryId);
        return mapper.map(category, CategoryDto.class);
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = repository.findAll();

        return categories.stream()
                .map(category -> mapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setId(categoryId);
        Category updatedCategory = repository.save(category);

        return mapper.map(updatedCategory, CategoryDto.class);
    }

    public void deleteCategory(Long categoryId) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        repository.delete(category);
    }
}
