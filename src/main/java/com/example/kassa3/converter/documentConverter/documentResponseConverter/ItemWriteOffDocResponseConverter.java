package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.model.document.ItemWriteOffDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemWriteOffDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemWriteOffDetailsResponseConverter.class})
public interface ItemWriteOffDocResponseConverter {

    @Mapping(target = "itemWriteOffDetailsList", source = "itemWriteOffDetailsList")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    ItemWriteOffDocResponseDto toDTO(ItemWriteOffDoc itemWriteOffDoc);

    List<ItemWriteOffDocResponseDto> toDTOList(List<ItemWriteOffDoc> itemWriteOffDocList);
}
