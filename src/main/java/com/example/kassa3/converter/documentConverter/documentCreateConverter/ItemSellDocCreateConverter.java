package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.converter.ShopConverter;
import com.example.kassa3.converter.UserCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemSellDocResolver;
import com.example.kassa3.model.document.ItemSellDoc;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemSellDocCreateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemSellDocResolver.class, ItemSellDetailsCreateConverter.class, PartnerConverter.class,
                ShopConverter.class, UserCreateConverter.class, DebitDocCreateConverter.class})
public interface ItemSellDocCreateConverter {

    ItemSellDoc toModel(Long itemSellDocId);

    @Mapping(ignore = true, target = "documentDateTime")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(source = "itemSellDetailsList", target = "itemSellDetailsList")
    @Mapping(source = "debitDoc", target = "debitDoc")
    @Mapping(source = "partnerId", target = "partner")
    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "userId", target = "user")
    ItemSellDoc toModel(ItemSellDocCreateDto itemSellDocCreateDto);

    List<ItemSellDoc> toModelList(List<ItemSellDocCreateDto> itemSellDocCreateDtoList);


}
