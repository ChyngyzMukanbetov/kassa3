package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.ItemCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemSellDetailsResolver;
import com.example.kassa3.model.document.ItemSellDetails;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemSellDetailsCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemSellDetailsResolver.class, ItemCreateConverter.class})
public interface ItemSellDetailsCreateConverter {
    ItemSellDetails toModel(Long itemSellDetailsId);

    @Mapping(source = "itemId", target = "item")
    @Mapping(ignore = true, target = "itemSellDoc")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(target = "nonCash", defaultValue = "false")
    @Mapping(target = "onDebit", defaultValue = "false")
    ItemSellDetails toModel(ItemSellDetailsCreateDto itemSellDetailsCreateDto);

    List<ItemSellDetails> toModelList(List<ItemSellDetailsCreateDto> itemSellDetailsCreateDtoList);
}
