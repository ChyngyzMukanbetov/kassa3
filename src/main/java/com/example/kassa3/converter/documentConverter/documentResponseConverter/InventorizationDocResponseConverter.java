package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.model.document.InventorizationDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.InventorizationDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {InventorizationDetailsResponseConverter.class})
public interface InventorizationDocResponseConverter {

    @Mapping(target = "inventorizationDetailsList", source = "inventorizationDetailsList")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    InventorizationDocResponseDto toDTO(InventorizationDoc inventorizationDoc);



    List<InventorizationDocResponseDto> toDTOList(List<InventorizationDoc> inventorizationDocList);
}
