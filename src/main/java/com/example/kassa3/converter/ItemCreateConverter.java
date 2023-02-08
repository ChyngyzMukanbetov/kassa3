package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.ItemResolver;
import com.example.kassa3.model.dto.ItemCreateDto;
import com.example.kassa3.model.entity.Item;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemResolver.class, UserConverter.class, ShopConverter.class, CategoryConverter.class, ColorConverter.class, MeasureConverter.class, CountryConverter.class})
public interface ItemCreateConverter {
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "shop", source = "shopId")
    @Mapping(target = "category", source = "categoryId")
    @Mapping(target = "color", source = "colorName")
    @Mapping(target = "measure", source = "measureName")
    @Mapping(target = "madeInCountry", source = "madeInCountryId")
    Item toModel(ItemCreateDto itemCreateDto);
}
