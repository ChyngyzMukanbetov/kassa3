package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.CategoryResolver;
import com.example.kassa3.model.dto.CategoryDto;
import com.example.kassa3.model.entity.Category;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {CategoryResolver.class, UserCreateConverter.class})
public interface CategoryConverter {

    Category toModel(Long categoryId);

    @Mapping(source = "user.id", target = "userId")
    CategoryDto toDTO(Category category);

    @Mapping(target = "user", source = "userId")
    Category toModel(CategoryDto categoryDto);

    List<Category> toModelList(List<CategoryDto> categoryDtoList);

    List<CategoryDto> toDTOList(List<Category> categoryList);
}
