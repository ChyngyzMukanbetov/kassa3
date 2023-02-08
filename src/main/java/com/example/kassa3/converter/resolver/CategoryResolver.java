package com.example.kassa3.converter.resolver;

import com.example.kassa3.model.dto.CategoryDto;
import com.example.kassa3.model.entity.Category;
import com.example.kassa3.service.abstracts.CategoryService;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryResolver {

    public final CategoryService categoryService;

    @ObjectFactory
    public Category resolve(Long categoryId, @TargetType Class<Category> type) {
        if (categoryId == null) {
            return null;
        } else {
            return categoryService.findById(categoryId);
        }
    }

    @ObjectFactory
    public Category resolve(CategoryDto dto, @TargetType Class<Category> type) {
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            return new Category();
        } else {
            return categoryService.findById(dto.getId());
        }
    }
}
