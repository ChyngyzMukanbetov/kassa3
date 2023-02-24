package com.example.kassa3.converter.documentConverter.documentCreateConverter;

import com.example.kassa3.converter.ItemCreateConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemWriteOffDetailsResolver;
import com.example.kassa3.model.document.ItemWriteOffDetails;
import com.example.kassa3.model.dto.documentDto.documentCreateDto.ItemWriteOffDetailsCreateDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemWriteOffDetailsResolver.class, ItemCreateConverter.class})
public interface ItemWriteOffDetailsCreateConverter {
    ItemWriteOffDetails toModel(Long itemWriteOffDetailsId);

    @Mapping(source = "itemId", target = "item")
    @Mapping(ignore = true, target = "itemWriteOffDoc")
    @Mapping(ignore = true, target = "docCode")
    @Mapping(ignore = true, target = "activate")
    @Mapping(ignore = true, target = "deactivateDate")
    ItemWriteOffDetails toModel(ItemWriteOffDetailsCreateDto itemWriteOffDetailsCreateDto);

    List<ItemWriteOffDetails> toModelList(List<ItemWriteOffDetailsCreateDto> itemWriteOffDetailsCreateDtoList);


}
