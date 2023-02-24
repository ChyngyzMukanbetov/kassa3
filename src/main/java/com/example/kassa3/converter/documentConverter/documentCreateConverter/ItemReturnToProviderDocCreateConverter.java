package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemReturnToProviderDocResolver;
import com.example.kassa3.model.document.ItemReturnToProviderDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnToProviderDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemReturnToProviderDocResolver.class, ItemReturnToProviderDetailsCreateConverter.class, PartnerConverter.class,
                ShopConverter.class, UserCreateConverter.class})
public interface ItemReturnToProviderDocCreateConverter {

    ItemReturnToProviderDoc toModel(Long itemReturnToProviderDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(source = "itemReturnToProviderDetailsList", target = "itemReturnToProviderDetailsList")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    ItemReturnToProviderDoc toModel(ItemReturnToProviderDocCreateDto itemReturnToProviderDocCreateDto);

    List<ItemReturnToProviderDoc> toModelList(List<ItemReturnToProviderDocCreateDto> itemReturnToProviderDocCreateDtoList);
}
