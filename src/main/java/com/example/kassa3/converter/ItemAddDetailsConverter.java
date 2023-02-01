package com.example.kassa3.converter;

import com.example.kassa3.model.dto.ItemAddDetailsDto;
import com.example.kassa3.model.entity.ItemAddDetails;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {ItemConverter.class})
public interface ItemAddDetailsConverter {

    @Mapping(source = "itemId", target = "item")
    ItemAddDetails toModel(ItemAddDetailsDto itemAddDetailsDto);
}
