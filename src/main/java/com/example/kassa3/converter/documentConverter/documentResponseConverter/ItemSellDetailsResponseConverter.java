package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.model.document.ItemSellDetails;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemSellDetailsResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface ItemSellDetailsResponseConverter {

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemSellDocId", source = "itemSellDoc.id")
    ItemSellDetailsResponseDto toDTO(ItemSellDetails itemSellDetails);

    List<ItemSellDetailsResponseDto> toDTOList(List<ItemSellDetails> itemSellDetailsList);



}
