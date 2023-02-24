package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemArrivalDocResolver;
import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemArrivalDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemArrivalDocResolver.class, ItemArrivalDetailsCreateConverter.class, PartnerConverter.class,
                ShopConverter.class, UserCreateConverter.class, CreditDocCreateConverter.class})
public interface ItemArrivalDocCreateConverter {

    ItemArrivalDoc toModel(Long itemArrivalDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(source = "itemArrivalDetailsList", target = "itemArrivalDetailsList")
    @Mapping(source = "creditDoc", target = "creditDoc")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    ItemArrivalDoc toModel(ItemArrivalDocCreateDto itemArrivalDocCreateDto);

    List<ItemArrivalDoc> toModelList(List<ItemArrivalDocCreateDto> itemArrivalDocCreateDtoList);


}