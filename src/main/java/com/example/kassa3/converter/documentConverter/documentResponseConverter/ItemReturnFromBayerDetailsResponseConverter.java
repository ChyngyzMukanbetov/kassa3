package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.model.document.ItemReturnFromBayerDetails;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemReturnFromBayerDetailsResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface ItemReturnFromBayerDetailsResponseConverter {

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemReturnFromBayerDocId", source = "itemReturnFromBayerDoc.id")
    ItemReturnFromBayerDetailsResponseDto toDTO(ItemReturnFromBayerDetails itemReturnFromBayerDetails);

    List<ItemReturnFromBayerDetailsResponseDto> toDTOList(List<ItemReturnFromBayerDetails> itemReturnFromBayerDetailsList);
}
