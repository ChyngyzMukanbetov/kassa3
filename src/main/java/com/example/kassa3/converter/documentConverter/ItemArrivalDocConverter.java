package com.example.kassa3.converter.documentConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemArrivalDocResolver;
import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.model.dto.documentDto.ItemArrivalDocDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemArrivalDocResolver.class, ItemArrivalDetailsConverter.class, PartnerConverter.class,
                ShopConverter.class, UserConverter.class, CreditDocConverter.class})
public interface ItemArrivalDocConverter {

    ItemArrivalDoc toModel(Long itemArrivalDocId);

    @Mapping(ignore = true, target = "documentData")
    @Mapping(source = "itemArrivalDetailsList", target = "itemArrivalDetailsList")
    @Mapping(source = "creditDoc", target = "creditDoc")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    ItemArrivalDoc toModel(ItemArrivalDocDto itemArrivalDocDto);

    @Mapping(target = "itemArrivalDetailsList", source = "itemArrivalDetailsList")
    @Mapping(target = "creditDoc", source = "creditDoc")
    @Mapping(target = "partnerId", source = "partner.id")
    @Mapping(target = "partnerName", source = "partner.name")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    ItemArrivalDocDto toDTO(ItemArrivalDoc itemArrivalDoc);

    List<ItemArrivalDoc> toModelList(List<ItemArrivalDocDto> itemArrivalDocDtoList);

    List<ItemArrivalDocDto> toDTOList(List<ItemArrivalDoc> itemArrivalDocList);
}