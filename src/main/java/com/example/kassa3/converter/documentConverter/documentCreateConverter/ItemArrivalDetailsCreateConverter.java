package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.ItemCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemArrivalDetailsResolver;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemArrivalDetailsCreateDto;
import com.example.kassa3.model.document.ItemArrivalDetails;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemArrivalDetailsResolver.class, ItemCreateConverter.class})
public interface ItemArrivalDetailsCreateConverter {

    ItemArrivalDetails toModel(Long itemArrivalDetailsId);

    @Mapping(source = "itemId", target = "item")
    @Mapping(ignore = true, target = "itemArrivalDoc")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(target = "useBasePriceWAM", defaultValue = "true")
    @Mapping(target = "usePriceWAM", defaultValue = "false")
    @Mapping(target = "nonCash", defaultValue = "false")
    @Mapping(target = "onCredit", defaultValue = "false")
    ItemArrivalDetails toModel(ItemArrivalDetailsCreateDto itemArrivalDetailsCreateDto);

    List<ItemArrivalDetails> toModelList(List<ItemArrivalDetailsCreateDto> itemArrivalDetailsCreateDtoList);


}