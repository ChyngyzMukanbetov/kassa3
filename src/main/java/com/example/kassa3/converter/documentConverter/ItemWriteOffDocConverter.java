package com.example.kassa3.converter.documentConverter;

import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemWriteOffDocResolver;
import com.example.kassa3.model.document.ItemWriteOffDoc;
import com.example.kassa3.model.dto.documentDto.ItemWriteOffDocDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemWriteOffDocResolver.class, ItemWriteOffDetailsConverter.class,
                ShopConverter.class, UserConverter.class})
public interface ItemWriteOffDocConverter {

    ItemWriteOffDoc toModel(Long itemWriteOffDocId);

    @Mapping(ignore = true, target = "documentData")
    @Mapping(source = "itemWriteOffDetailsList", target = "itemWriteOffDetailsList")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    ItemWriteOffDoc toModel(ItemWriteOffDocDto itemWriteOffDocDto);

    @Mapping(target = "itemWriteOffDetailsList", source = "itemWriteOffDetailsList")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    ItemWriteOffDocDto toDTO(ItemWriteOffDoc itemWriteOffDoc);

    List<ItemWriteOffDoc> toModelList(List<ItemWriteOffDocDto> itemWriteOffDocDtoList);

    List<ItemWriteOffDocDto> toDTOList(List<ItemWriteOffDoc> itemWriteOffDocList);
}