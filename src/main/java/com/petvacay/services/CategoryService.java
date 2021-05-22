package com.petvacay.services;

import com.petvacay.dto.category.CategoryDTO;
import com.petvacay.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoriesByIds(List<Long> categoryIds);

    List<CategoryDTO> getAllCategories();
}
