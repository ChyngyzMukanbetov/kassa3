package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.model.document.ItemWriteOffDetails;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemWriteOffDetailsResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface ItemWriteOffDetailsResponseConverter {

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemWriteOffDocId", source = "itemWriteOffDoc.id")
    ItemWriteOffDetailsResponseDto toDTO(ItemWriteOffDetails itemWriteOffDetails);

    List<ItemWriteOffDetailsResponseDto> toDTOList(List<ItemWriteOffDetails> itemWriteOffDetailsList);
}
