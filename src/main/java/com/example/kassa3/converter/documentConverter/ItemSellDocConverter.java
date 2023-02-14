package com.example.kassa3.converter.documentConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemSellDocResolver;
import com.example.kassa3.model.document.ItemSellDoc;
import com.example.kassa3.model.dto.documentDto.ItemSellDocDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemSellDocResolver.class, ItemSellDetailsConverter.class, PartnerConverter.class,
                ShopConverter.class, UserConverter.class, DebitDocConverter.class})
public interface ItemSellDocConverter {

    ItemSellDoc toModel(Long itemSellDocId);

    @Mapping(ignore = true, target = "documentData")
    @Mapping(source = "itemSellDetailsList", target = "itemSellDetailsList")
    @Mapping(source = "debitDoc", target = "debitDoc")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    ItemSellDoc toModel(ItemSellDocDto itemSellDocDto);

    @Mapping(target = "itemSellDetailsList", source = "itemSellDetailsList")
    @Mapping(target = "debitDoc", source = "debitDoc")
    @Mapping(target = "partnerId", source = "partner.id")
    @Mapping(target = "partnerName", source = "partner.name")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    ItemSellDocDto toDTO(ItemSellDoc itemSellDoc);

    List<ItemSellDoc> toModelList(List<ItemSellDocDto> itemSellDocDtoList);

    List<ItemSellDocDto> toDTOList(List<ItemSellDoc> itemSellDocList);
}
