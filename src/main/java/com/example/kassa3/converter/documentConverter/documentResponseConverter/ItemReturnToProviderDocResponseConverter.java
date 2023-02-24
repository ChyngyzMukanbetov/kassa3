package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.model.document.ItemReturnToProviderDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemReturnToProviderDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemReturnToProviderDetailsResponseConverter.class, PartnerConverter.class})
public interface ItemReturnToProviderDocResponseConverter {

    @Mapping(target = "itemReturnToProviderDetailsList", source = "itemReturnToProviderDetailsList")
    @Mapping(target = "partner", source = "partner")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    ItemReturnToProviderDocResponseDto toDTO(ItemReturnToProviderDoc itemReturnToProviderDoc);

    List<ItemReturnToProviderDocResponseDto> toDTOList(List<ItemReturnToProviderDoc> itemReturnToProviderDocList);
}
