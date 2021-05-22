package com.petvacay.services.implementation;

import com.petvacay.dto.category.CategoryDTO;
import com.petvacay.entities.Category;
import com.petvacay.mappers.category.CategoryMapper;
import com.petvacay.repositories.CategoryRepository;
import com.petvacay.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> getCategoriesByIds(List<Long> categoryIds) {
        List<Category> requiredCategories = new ArrayList<>();
        categoryIds.forEach((categoryId) ->
                requiredCategories.add(categoryRepository.getOne(categoryId)));
        return requiredCategories;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryMapper.convertListToDto(categoryRepository.findAll());
    }
}
