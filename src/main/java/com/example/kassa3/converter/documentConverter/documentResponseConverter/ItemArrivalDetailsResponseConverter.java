package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.model.document.ItemArrivalDetails;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemArrivalDetailsResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface ItemArrivalDetailsResponseConverter {

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemArrivalDocId", source = "itemArrivalDoc.id")
    ItemArrivalDetailsResponseDto toDTO(ItemArrivalDetails itemArrivalDetails);

    List<ItemArrivalDetailsResponseDto> toDTOList(List<ItemArrivalDetails> itemArrivalDetailsList);
}
