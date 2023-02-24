package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.ItemCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.InventorizationDetailsResolver;
import com.example.kassa3.model.document.InventorizationDetails;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.InventorizationDetailsCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {InventorizationDetailsResolver.class, ItemCreateConverter.class})
public interface InventorizationDetailsCreateConverter {

    InventorizationDetails toModel(Long inventorizationDetailsId);

    @Mapping(source = "itemId", target = "item")
    @Mapping(ignore = true, target = "inventorizationDoc")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    InventorizationDetails toModel(InventorizationDetailsCreateDto inventorizationDetailsCreateDto);

    List<InventorizationDetails> toModelList(List<InventorizationDetailsCreateDto> inventorizationDetailsCreateDtoList);
}
