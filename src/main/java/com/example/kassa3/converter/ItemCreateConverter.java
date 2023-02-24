package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.ItemResolver;
import com.example.kassa3.model.dto.ItemCreateDto;
import com.example.kassa3.model.dto.ItemResponseDto;
import com.example.kassa3.model.entity.Item;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemResolver.class, UserCreateConverter.class, ShopConverter.class, CategoryConverter.class, ColorConverter.class,
                MeasureConverter.class, CountryConverter.class, ImageConverter.class})
public interface ItemCreateConverter {
    Item toModel(Long itemId);

    @Mapping(target = "user", source = "userId")
    @Mapping(target = "shop", source = "shopId")
    @Mapping(target = "category", source = "categoryId")
    @Mapping(target = "color", source = "colorName")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "measure", source = "measureName")
    @Mapping(target = "madeInCountry", source = "madeInCountryId")
    Item toModel(ItemCreateDto itemCreateDto);

    List<Item> toModelList(List<ItemResponseDto> itemResponseDtoList);
}
