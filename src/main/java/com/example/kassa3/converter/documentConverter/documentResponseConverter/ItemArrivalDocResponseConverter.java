package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.model.document.ItemArrivalDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemArrivalDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemArrivalDetailsResponseConverter.class, PartnerConverter.class, CreditDocResponseConverter.class})
public interface ItemArrivalDocResponseConverter {

    @Mapping(target = "itemArrivalDetailsList", source = "itemArrivalDetailsList")
    @Mapping(target = "creditDoc", source = "creditDoc")
    @Mapping(target = "partner", source = "partner")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    ItemArrivalDocResponseDto toDTO(ItemArrivalDoc itemArrivalDoc);



    List<ItemArrivalDocResponseDto> toDTOList(List<ItemArrivalDoc> itemArrivalDocList);
}
