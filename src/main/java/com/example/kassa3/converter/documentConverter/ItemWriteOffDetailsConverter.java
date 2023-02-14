package com.example.kassa3.converter.documentConverter;

import com.example.kassa3.converter.ItemConverter;
import com.example.kassa3.converter.resolver.documentResolver.ItemWriteOffDetailsResolver;
import com.example.kassa3.model.document.ItemWriteOffDetails;
import com.example.kassa3.model.dto.documentDto.ItemWriteOffDetailsDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemWriteOffDetailsResolver.class, ItemConverter.class})
public interface ItemWriteOffDetailsConverter {
    ItemWriteOffDetails toModel(Long itemWriteOffDetailsId);

    @Mapping(source = "itemId", target = "item")
    @Mapping(ignore = true, target = "itemWriteOffDoc")
    ItemWriteOffDetails toModel(ItemWriteOffDetailsDto itemWriteOffDetailsDto);

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemWriteOffDocId", source = "itemWriteOffDoc.id")
    ItemWriteOffDetailsDto toDTO(ItemWriteOffDetails itemWriteOffDetails);

    @Mapping(source = "itemId", target = "item")
    @Mapping(ignore = true, target = "itemWriteOffDoc")
    List<ItemWriteOffDetails> toModelList(List<ItemWriteOffDetailsDto> itemWriteOffDetailsDtoList);

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemWriteOffDocId", source = "itemWriteOffDoc.id")
    List<ItemWriteOffDetailsDto> toDTOList(List<ItemWriteOffDetails> itemWriteOffDetailsList);
}
