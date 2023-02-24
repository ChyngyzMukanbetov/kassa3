package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.model.document.ItemReturnToProviderDetails;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemReturnToProviderDetailsResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface ItemReturnToProviderDetailsResponseConverter {

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemReturnToProviderDocId", source = "itemReturnToProviderDoc.id")
    ItemReturnToProviderDetailsResponseDto toDTO(ItemReturnToProviderDetails itemReturnToProviderDetails);

    List<ItemReturnToProviderDetailsResponseDto> toDTOList(List<ItemReturnToProviderDetails> itemReturnToProviderDetailsList);
}
