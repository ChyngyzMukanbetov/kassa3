package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.ItemResolver;
import com.example.kassa3.model.dto.ItemDto;
import com.example.kassa3.model.entity.Item;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {ItemResolver.class})
public interface ItemConverter {

    Item toModel(Long itemId);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "category.id", target = "categoryId")
    ItemDto toDTO(Item item);

    Item toModel(ItemDto itemDto);

    List<Item> toModelList(List<ItemDto> itemDtoList);

    List<ItemDto> toDTOList(List<Item> itemList);
}