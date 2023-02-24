package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemWriteOffDocResolver;
import com.example.kassa3.model.document.ItemWriteOffDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemWriteOffDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemWriteOffDocResolver.class, ItemWriteOffDetailsCreateConverter.class,
                ShopConverter.class, UserCreateConverter.class})
public interface ItemWriteOffDocCreateConverter {

    ItemWriteOffDoc toModel(Long itemWriteOffDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(source = "itemWriteOffDetailsList", target = "itemWriteOffDetailsList")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    ItemWriteOffDoc toModel(ItemWriteOffDocCreateDto itemWriteOffDocCreateDto);

    List<ItemWriteOffDoc> toModelList(List<ItemWriteOffDocCreateDto> itemWriteOffDocCreateDtoList);


}