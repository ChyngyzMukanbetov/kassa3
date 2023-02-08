package com.example.kassa3.converter;

import com.example.kassa3.model.dto.documentDto.ItemArrivalDetailsDto;
import com.example.kassa3.model.document.ItemArrivalDetails;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = {ItemConverter.class})
public interface ItemAddDetailsConverter {

    @Mapping(source = "itemId", target = "item")
    ItemArrivalDetails toModel(ItemArrivalDetailsDto itemArrivalDetailsDto);
}
