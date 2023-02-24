package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.model.document.ItemReturnFromBayerDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemReturnFromBayerDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemReturnFromBayerDetailsResponseConverter.class, PartnerConverter.class})
public interface ItemReturnFromBayerDocResponseConverter {

    @Mapping(target = "itemReturnFromBayerDetailsList", source = "itemReturnFromBayerDetailsList")
    @Mapping(target = "partner", source = "partner")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    ItemReturnFromBayerDocResponseDto toDTO(ItemReturnFromBayerDoc itemReturnFromBayerDoc);



    List<ItemReturnFromBayerDocResponseDto> toDTOList(List<ItemReturnFromBayerDoc> itemReturnFromBayerDocList);
}
