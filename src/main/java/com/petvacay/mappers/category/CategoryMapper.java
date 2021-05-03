package com.petvacay.mappers.category;

import com.petvacay.dto.category.CategoryDTO;
import com.petvacay.entities.Category;
import com.petvacay.mappers.GeneralMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper implements GeneralMapper<Category, CategoryDTO> {
    @Override
    public CategoryDTO convertToDto(Category model) {
        return CategoryDTO.builder()
                .categoryId(model.getCategoryId())
                .categoryName(model.getCategoryName())
                .build();
    }

    @Override
    public Category convertToModel(CategoryDTO dto) {
        return Category.builder()
                .categoryId(dto.getCategoryId())
                .categoryName(dto.getCategoryName())
                .build();
    }

    public List<CategoryDTO> convertListToDto(List<Category> models) {
        List<CategoryDTO> dtos = new ArrayList<>();
        models.forEach(model -> dtos.add(convertToDto(model)));
        return dtos;
    }
}
