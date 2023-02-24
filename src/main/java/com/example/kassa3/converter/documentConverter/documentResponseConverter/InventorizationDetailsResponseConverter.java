package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.model.document.InventorizationDetails;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.InventorizationDetailsResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface InventorizationDetailsResponseConverter {

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "inventorizationDocId", source = "inventorizationDoc.id")
    InventorizationDetailsResponseDto toDTO(InventorizationDetails inventorizationDetails);

    List<InventorizationDetailsResponseDto> toDTOList(List<InventorizationDetails> inventorizationDetailsList);
}
