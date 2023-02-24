package com.example.kassa3.converter.documentConverter.documentResponseConverter;

import com.example.kassa3.converter.PartnerConverter;
import com.example.kassa3.model.document.ItemSellDoc;
import com.example.kassa3.model.dto.documentDto.documentResponseDto.ItemSellDocResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true),
        uses = {ItemSellDetailsResponseConverter.class, PartnerConverter.class, DebitDocResponseConverter.class})
public interface ItemSellDocResponseConverter {

    @Mapping(target = "itemSellDetailsList", source = "itemSellDetailsList")
    @Mapping(target = "debitDoc", source = "debitDoc")
    @Mapping(target = "partner", source = "partner")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    ItemSellDocResponseDto toDTO(ItemSellDoc itemSellDoc);

    List<ItemSellDocResponseDto> toDTOList(List<ItemSellDoc> itemSellDocList);
}
