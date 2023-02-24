package com.example.kassa3.converter;

import com.example.kassa3.converter.resolver.ShopResolver;
import com.example.kassa3.model.dto.ShopDto;
import com.example.kassa3.model.entity.*;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {UserCreateConverter.class, ShopResolver.class, AddressConverter.class, ItemResponseConverter.class})
public interface ShopConverter {
    Shop toModel(Long shopId);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "items", target = "itemResponseDtoList")
    ShopDto toDTO(Shop shop);

    @Mapping(target = "user", source = "userId")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "items", ignore = true)
    Shop toModel(ShopDto shopDto);

    List<Shop> toModelList(List<ShopDto> shopDtoList);

    List<ShopDto> toDTOList(List<Shop> shopList);
}
