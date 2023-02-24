package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemReturnFromBayerDocResolver;
import com.example.kassa3.model.document.ItemReturnFromBayerDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnFromBayerDocCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemReturnFromBayerDocResolver.class, ItemReturnFromBayerDetailsCreateConverter.class, PartnerConverter.class,
                ShopConverter.class, UserCreateConverter.class})
public interface ItemReturnFromBayerDocCreateConverter {

    ItemReturnFromBayerDoc toModel(Long itemReturnFromBayerDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(source = "itemReturnFromBayerDetailsList", target = "itemReturnFromBayerDetailsList")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    ItemReturnFromBayerDoc toModel(ItemReturnFromBayerDocCreateDto itemReturnFromBayerDocCreateDto);

    List<ItemReturnFromBayerDoc> toModelList(List<ItemReturnFromBayerDocCreateDto> itemReturnFromBayerDocCreateDtoList);
}
