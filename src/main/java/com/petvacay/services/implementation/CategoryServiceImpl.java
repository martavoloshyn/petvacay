package com.petvacay.services.implementation;

import com.petvacay.entities.Category;
import com.petvacay.repositories.CategoryRepository;
import com.petvacay.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategoriesByIds(List<Long> categoryIds) {
        List<Category> requiredCategories = new ArrayList<>();
        categoryIds.forEach((categoryId) ->
                requiredCategories.add(categoryRepository.getOne(categoryId)));
        return requiredCategories;
    }
}
