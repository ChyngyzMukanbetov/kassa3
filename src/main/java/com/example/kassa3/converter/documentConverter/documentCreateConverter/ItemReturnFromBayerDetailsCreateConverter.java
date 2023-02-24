package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.ItemCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemReturnFromBayerDetailsResolver;
import com.example.kassa3.model.document.ItemReturnFromBayerDetails;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnFromBayerDetailsCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemReturnFromBayerDetailsResolver.class, ItemCreateConverter.class})
public interface ItemReturnFromBayerDetailsCreateConverter {

    ItemReturnFromBayerDetails toModel(Long itemReturnFromBayerDetailsId);

    @Mapping(source = "itemId", target = "item")
    @Mapping(ignore = true, target = "itemReturnFromBayerDoc")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(target = "nonCash", defaultValue = "false")
    ItemReturnFromBayerDetails toModel(ItemReturnFromBayerDetailsCreateDto itemReturnFromBayerDetailsCreateDto);

    List<ItemReturnFromBayerDetails> toModelList(List<ItemReturnFromBayerDetailsCreateDto> itemReturnFromBayerDetailsCreateDtoList);
}
