package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.ItemCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemReturnToProviderDetailsResolver;
import com.example.kassa3.model.document.ItemReturnToProviderDetails;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemReturnToProviderDetailsCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemReturnToProviderDetailsResolver.class, ItemCreateConverter.class})
public interface ItemReturnToProviderDetailsCreateConverter {

    ItemReturnToProviderDetails toModel(Long itemReturnToProviderDetailsId);

    @Mapping(source = "itemId", target = "item")
    @Mapping(ignore = true, target = "itemReturnToProviderDoc")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    @Mapping(target = "nonCash", defaultValue = "false")
    ItemReturnToProviderDetails toModel(ItemReturnToProviderDetailsCreateDto itemReturnToProviderDetailsCreateDto);

    List<ItemReturnToProviderDetails> toModelList(List<ItemReturnToProviderDetailsCreateDto> itemReturnToProviderDetailsCreateDtoList);
}
