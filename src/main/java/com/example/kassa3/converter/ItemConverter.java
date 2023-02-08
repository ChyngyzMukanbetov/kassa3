package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.ItemResolver;
import com.example.kassa3.model.dto.ItemDto;
import com.example.kassa3.model.entity.Item;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = {ItemResolver.class, CategoryConverter.class})
public interface ItemConverter {

    Item toModel(Long itemId);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "category.id", target = "categoryId")
    ItemDto toDTO(Item item);

    List<ItemDto> toDTOList(List<Item> itemList);
}