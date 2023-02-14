package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.ItemResolver;
import com.example.kassa3.model.dto.ItemDto;
import com.example.kassa3.model.entity.Item;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemResolver.class, CategoryConverter.class, ImageConverter.class})
public interface ItemConverter {

    Item toModel(Long itemId);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "userName")
    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "shop.shopName", target = "shopName")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "madeInCountry.id", target = "madeInCountryId")
    @Mapping(source = "madeInCountry.name", target = "madeInCountryName")
    @Mapping(source = "color.rusName", target = "colorName")
    @Mapping(source = "measure.rusName", target = "measureName")
    @Mapping(source = "image", target = "image")
    ItemDto toDTO(Item item);

    List<ItemDto> toDTOList(List<Item> itemList);
}